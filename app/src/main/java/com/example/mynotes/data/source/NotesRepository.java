package com.example.mynotes.data.source;

import com.example.mynotes.data.Note;

import java.util.List;

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
    public void getNotes(LoadNotesCallback callback) {
        notesLocalDataSource.getNotes(new LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                callback.onNotesLoaded(notes);
            }

            @Override
            public void onDataNotAvailable() {
                notesRemoteDataSource.getNotes(new LoadNotesCallback() {
                    @Override
                    public void onNotesLoaded(List<Note> notes) {
                        //TODO
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }
}
