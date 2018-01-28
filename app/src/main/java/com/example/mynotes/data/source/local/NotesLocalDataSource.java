package com.example.mynotes.data.source.local;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;

import java.util.List;

import io.reactivex.Completable;
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
    public Observable<List<Note>> getNotes() {
        return notesDao.getAll().toObservable();
    }

    @Override
    public Observable<Note> getNoteById(int noteId) {
        return notesDao.getById(noteId).toObservable();
    }

    @Override
    public Completable createNote(Note note) {
        return Completable.fromAction(() -> notesDao.insert(note));
    }

    @Override
    public Completable updateNote(Note note) {
        return Completable.fromAction(() -> notesDao.update(note));
    }

    @Override
    public Completable deleteNoteById(int noteId) {
        return Completable.fromAction(() -> notesDao.deleteById(noteId));
    }
}
