package com.example.mynotes.notes;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;
import com.example.mynotes.data.source.NotesRepository;

import java.util.List;

/**
 * Created by mateus on 23/01/18.
 */

public class NotesPresenter implements NotesContract.Presenter {

    private final NotesContract.View view;
    private final NotesRepository repository;

    public NotesPresenter(NotesContract.View view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadNotes();
    }

    private void loadNotes() {
        repository.getNotes(new NotesDataSource.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                if(!view.isActive()) {
                    return;
                }
                view.showNotes(notes);
            }

            @Override
            public void onDataNotAvailable() {
                if(!view.isActive()) {
                    return;
                }
                view.showLoadingNotesError();
            }
        });
    }
}
