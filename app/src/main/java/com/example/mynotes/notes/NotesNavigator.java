package com.example.mynotes.notes;

import static com.example.mynotes.addeditnote.AddEditNoteActivity.EXTRA_NOTE_ID;

import com.example.mynotes.addeditnote.AddEditNoteActivity;
import com.example.mynotes.util.providers.BaseNavigator;

/**
 * Created by mateus on 27/01/18.
 */

public class NotesNavigator {

    private final BaseNavigator provider;

    public NotesNavigator(BaseNavigator provider) {
        this.provider = provider;
    }

    void openAddEditNotePage(int noteId) {
        provider.startActivityForResultWithExtra(AddEditNoteActivity.class, 11, EXTRA_NOTE_ID,
                noteId);
    }
}
