package com.example.mynotes.addeditnote;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;
import com.example.mynotes.data.source.NotesRepository;

/**
 * Created by mateus on 25/01/18.
 */

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    private final AddEditNoteContract.View view;
    private final NotesRepository repository;
    private final int noteId;

    AddEditNotePresenter(AddEditNoteContract.View view, NotesRepository repository, int noteId) {
        this.view = view;
        this.repository = repository;
        this.noteId = noteId;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        if (noteId == 0) {
            view.hideDeleteOption();
        } else {
            repository.getNoteById(noteId, new NotesDataSource.GetNoteCallback() {
                @Override
                public void onNoteLoaded(Note note) {
                    view.fillForm(note.getTitle(), note.getDescription());
                }

                @Override
                public void onDataNotAvailable() {
                    view.showLoadingNotesError();
                    view.showNotesList();
                }
            });
        }
    }

    @Override
    public void saveNote(String title, String description) {
        if (noteId == 0) {
            createNote(title, description);
        } else {
            updateNote(title, description);
        }
        view.showNotesList();
    }

    @Override
    public void deleteNote() {
        repository.deleteNoteById(noteId);
        view.showNotesList();
    }

    private void createNote(String title, String description) {
        repository.createNote(new Note(title, description));
    }

    private void updateNote(String title, String description) {
        repository.updateNote(new Note(noteId, title, description));
    }
}
