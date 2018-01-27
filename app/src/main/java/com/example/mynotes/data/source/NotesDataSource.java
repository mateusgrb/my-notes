package com.example.mynotes.data.source;

import com.example.mynotes.data.Note;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by mateus on 24/01/18.
 */

public interface NotesDataSource {

    Flowable<List<Note>> getNotes();

    Observable<Note> getNoteById(int noteId);

    void createNote(Note note);

    void updateNote(Note note);

    void deleteNoteById(int noteId);

    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);

        void onDataNotAvailable();
    }
}
