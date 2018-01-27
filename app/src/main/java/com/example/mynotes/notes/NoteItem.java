package com.example.mynotes.notes;

import com.example.mynotes.data.Note;

import io.reactivex.functions.Action;

/**
 * Created by mateus on 27/01/18.
 */

public class NoteItem {

    private final Note note;
    private final Action onClickAction;

    public NoteItem(Note note, Action onClickAction) {
        this.note = note;
        this.onClickAction = onClickAction;
    }

    public Note getNote() {
        return note;
    }

    public Action getOnClickAction() {
        return onClickAction;
    }
}
