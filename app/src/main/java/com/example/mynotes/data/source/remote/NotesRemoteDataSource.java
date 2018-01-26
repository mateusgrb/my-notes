package com.example.mynotes.data.source.remote;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;

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
    public void getNotes(LoadNotesCallback callback) {

    }

    @Override
    public void createNote(Note note) {

    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void getNoteById(int noteId, GetNoteCallback callback) {

    }
}
