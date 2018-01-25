package com.example.mynotes.data.source;

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

    }
}
