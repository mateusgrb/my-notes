package com.example.mynotes.addeditnote;

import com.example.mynotes.data.source.NotesRepository;

/**
 * Created by mateus on 25/01/18.
 */

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    private final AddEditNoteContract.View view;
    private final NotesRepository repository;
    private final int noteId;

    public AddEditNotePresenter(AddEditNoteContract.View view, NotesRepository repository,
            int noteId) {
        this.view = view;
        this.repository = repository;
        this.noteId = noteId;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
