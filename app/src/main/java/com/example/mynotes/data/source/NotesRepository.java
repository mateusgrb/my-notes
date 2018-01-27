package com.example.mynotes.data.source;

import com.example.mynotes.data.Note;

import java.util.List;

import io.reactivex.Flowable;
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
    public Flowable<List<Note>> getNotes() {
        return notesLocalDataSource.getNotes();
    }

    @Override
    public Observable<Note> getNoteById(int noteId) {
        return notesLocalDataSource.getNoteById(noteId);
    }

    @Override
    public void createNote(Note note) {
        notesLocalDataSource.createNote(note);
    }

    @Override
    public void updateNote(Note note) {
        notesLocalDataSource.updateNote(note);
    }

    @Override
    public void deleteNoteById(int noteId) {
        notesLocalDataSource.deleteNoteById(noteId);
    }
}
