package com.example.mynotes.notes;

import com.example.mynotes.R;
import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by mateus on 27/01/18.
 */

public class NotesViewModel {

    private final NotesRepository repository;

    private final PublishSubject<Integer> errorText;

    public NotesViewModel(NotesRepository repository) {
        this.repository = repository;

        errorText = PublishSubject.create();
    }

    public Flowable<List<Note>> getNotes() {
        return repository.getNotes().doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                errorText.onNext(R.string.error_loading_notes);
            }
        });
    }

    public PublishSubject<Integer> getErrorMessage() {
        return errorText;
    }
}
