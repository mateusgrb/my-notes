package com.example.mynotes.data.source;

import com.example.mynotes.data.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by mateus on 24/01/18.
 */

public class NotesRepository implements NotesDataSource {

    private static NotesRepository INSTANCE = null;

    private final NotesDataSource notesRemoteDataSource;

    private final NotesDataSource notesLocalDataSource;

    public static NotesRepository getInstance(NotesDataSource notesLocalDataSource,
            NotesDataSource notesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NotesRepository(notesLocalDataSource, notesRemoteDataSource);
        }
        return INSTANCE;
    }

    private NotesRepository(NotesDataSource notesLocalDataSource,
            NotesDataSource notesRemoteDataSource) {
        this.notesLocalDataSource = notesLocalDataSource;
        this.notesRemoteDataSource = notesRemoteDataSource;
    }

    @Override
    public Observable<List<Note>> getNotes() {
        return notesLocalDataSource.getNotes();
    }

    @Override
    public Observable<Note> getNoteById(int noteId) {
        return notesLocalDataSource.getNoteById(noteId);
    }

    @Override
    public Completable createNote(Note note) {
        return notesLocalDataSource.createNote(note);
    }

    @Override
    public Completable updateNote(Note note) {
        return notesLocalDataSource.updateNote(note);
    }

    @Override
    public Completable deleteNoteById(int noteId) {
        return notesLocalDataSource.deleteNoteById(noteId);
    }
}
