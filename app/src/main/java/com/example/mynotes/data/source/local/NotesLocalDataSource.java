package com.example.mynotes.data.source.local;

import com.example.mynotes.data.source.NotesDataSource;

/**
 * Created by mateus on 24/01/18.
 */

public class NotesLocalDataSource implements NotesDataSource {

    private static volatile NotesLocalDataSource INSTANCE;

    public static NotesLocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (NotesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesLocalDataSource();
                }
            }
        }
        return INSTANCE;
    }

    private NotesLocalDataSource() {
    }

    @Override
    public void getNotes(LoadNotesCallback callback) {

    }
}
