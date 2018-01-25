package com.example.mynotes.data.source;

import com.example.mynotes.data.Note;

import java.util.List;

/**
 * Created by mateus on 24/01/18.
 */

public interface NotesDataSource {

    void getNotes(LoadNotesCallback callback);

    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }
}
