package com.example.mynotes.notes;

import com.example.mynotes.BasePresenter;
import com.example.mynotes.BaseView;
import com.example.mynotes.data.Note;

import java.util.List;

/**
 * Created by mateus on 23/01/18.
 */

public interface NotesContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();
        void showNotes(List<Note> notes);
        void showLoadingNotesError();
        void showAddEditNotePage(int noteId);
    }

    interface Presenter extends BasePresenter {

        void addNewNote();
    }
}
