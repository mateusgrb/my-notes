package com.example.mynotes.notes;

import com.example.mynotes.R;
import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by mateus on 27/01/18.
 */

public class NotesViewModel {

    private final NotesRepository repository;

    private final PublishSubject<Integer> errorText;
    private final NotesNavigator navigator;

    public NotesViewModel(NotesRepository repository, NotesNavigator navigator) {
        this.repository = repository;
        this.navigator = navigator;
        errorText = PublishSubject.create();
    }

    public Flowable<List<NoteItem>> getNotes() {
        return repository.getNotes()
                .doOnError(throwable -> errorText.onNext(R.string.error_loading_notes))
                .map(notes -> {
                    List<NoteItem> noteItems = new ArrayList<>();
                    for (Note note : notes) {
                        noteItems.add(new NoteItem(note, () -> onNoteClicked(note)));
                    }
                    return noteItems;
                });
    }

    public void openAddNewNotePage() {
        navigator.openAddEditNotePage(0);
    }

    private void onNoteClicked(Note note) {
        navigator.openAddEditNotePage(note.getId());
    }

    public PublishSubject<Integer> getErrorMessage() {
        return errorText;
    }
}
