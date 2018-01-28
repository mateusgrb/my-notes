package com.example.mynotes.addeditnote;

import com.example.mynotes.R;
import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by mateus on 27/01/18.
 */

public class AddEditNoteViewModel {

    private final NotesRepository repository;
    private final AddEditNoteNavigator navigator;
    private int noteId;

    private final PublishSubject<Integer> errorMessage;

    private final PublishSubject<Boolean> showDeleteOption;

    public AddEditNoteViewModel(NotesRepository repository, AddEditNoteNavigator navigator,
            int noteId) {
        this.repository = repository;
        this.navigator = navigator;
        this.noteId = noteId;

        errorMessage = PublishSubject.create();
        showDeleteOption = PublishSubject.create();
    }

    public Observable<Note> getNote() {
        if (noteId == 0) {
            showDeleteOption.onNext(false);
            return Observable.empty();
        }
        return repository.getNoteById(noteId).doOnError(
                throwable -> {
                    errorMessage.onNext(R.string.error_loading_note);
                    navigator.onNoteUpdated();
                });
    }

    public PublishSubject<Integer> getErrorMessage() {
        return errorMessage;
    }

    public PublishSubject<Boolean> getShowDeleteOption() {
        return showDeleteOption;
    }

    public Completable saveNote(String title, String description) {
        Completable completable;
        if (noteId == 0) {
            completable = repository.createNote(new Note(title, description));
        } else {
            completable = repository.updateNote(new Note(noteId, title, description));
        }
        return completable.doOnComplete(navigator::onNoteUpdated);
    }

    public Completable deleteNote() {
        return repository.deleteNoteById(noteId).doOnComplete(navigator::onNoteUpdated);
    }
}
