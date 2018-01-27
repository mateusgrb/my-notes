package com.example.mynotes.data.source.local;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by mateus on 24/01/18.
 */

public class NotesLocalDataSource implements NotesDataSource {

    private static volatile NotesLocalDataSource INSTANCE;
    private final NotesDao notesDao;

    public static NotesLocalDataSource getInstance(NotesDao notesDao) {
        if (INSTANCE == null) {
            synchronized (NotesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesLocalDataSource(notesDao);
                }
            }
        }
        return INSTANCE;
    }

    private NotesLocalDataSource(NotesDao notesDao) {
        this.notesDao = notesDao;
    }

    @Override
    public Flowable<List<Note>> getNotes() {
        return notesDao.getAll();
    }

    @Override
    public Observable<Note> getNoteById(int noteId) {
        return null;
    }

    @Override
    public void createNote(Note note) {
        new Thread(() -> notesDao.insert(note)).start();
    }

    @Override
    public void updateNote(Note note) {
        new Thread(() -> notesDao.update(note)).start();
    }

    @Override
    public void deleteNoteById(int noteId) {
        new Thread(() -> notesDao.deleteById(noteId)).start();
    }
}
