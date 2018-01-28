package com.example.mynotes.data.source;

import com.example.mynotes.data.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by mateus on 24/01/18.
 */

public interface NotesDataSource {

    Observable<List<Note>> getNotes();

    Observable<Note> getNoteById(int noteId);

    Completable createNote(Note note);

    Completable updateNote(Note note);

    Completable deleteNoteById(int noteId);

}
