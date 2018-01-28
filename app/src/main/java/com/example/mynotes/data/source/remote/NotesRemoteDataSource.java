package com.example.mynotes.data.source.remote;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by mateus on 24/01/18.
 */

public class NotesRemoteDataSource implements NotesDataSource {

    private static NotesRemoteDataSource INSTANCE;

    public static NotesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotesRemoteDataSource();
        }
        return INSTANCE;
    }

    private NotesRemoteDataSource() {
    }

    @Override
    public Observable<List<Note>> getNotes() {
        return null;
    }

    @Override
    public Observable<Note> getNoteById(int noteId) {
        return null;
    }

    @Override
    public Completable createNote(Note note) {
        return null;
    }

    @Override
    public Completable updateNote(Note note) {
        return null;
    }

    @Override
    public Completable deleteNoteById(int noteId) {
        return null;
    }
}
