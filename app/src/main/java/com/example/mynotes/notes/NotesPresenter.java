package com.example.mynotes.notes;

/**
 * Created by mateus on 23/01/18.
 */

public class NotesPresenter implements NotesContract.Presenter {

    private final NotesContract.View view;

    public NotesPresenter(NotesContract.View view) {
        this.view = view;
    }
}
