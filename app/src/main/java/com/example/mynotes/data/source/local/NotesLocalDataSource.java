package com.example.mynotes.data.source.local;

import android.os.Handler;
import android.os.Looper;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;

import java.util.List;

/**
 * Created by mateus on 24/01/18.
 */

public class NotesLocalDataSource implements NotesDataSource {

    private static volatile NotesLocalDataSource INSTANCE;
    private final NotesDao notesDao;
    private final Handler handler = new Handler(Looper.getMainLooper());

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
    public void getNotes(final LoadNotesCallback callback) {
        new Thread(() -> {
            final List<Note> allNotes = notesDao.getAllNotes();
            handler.post(() -> {
                if (allNotes.isEmpty()) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onNotesLoaded(allNotes);
                }
            });
        }).start();
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
    public void getNoteById(int noteId, GetNoteCallback callback) {
        new Thread(() -> {
            Note note = notesDao.getNoteById(noteId);
            handler.post(() -> {
                if (note == null) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onNoteLoaded(note);
                }
            });
        }).start();
    }
}
