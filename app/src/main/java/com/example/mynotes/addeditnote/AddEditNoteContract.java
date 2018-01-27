package com.example.mynotes.addeditnote;

import com.example.mynotes.BasePresenter;
import com.example.mynotes.BaseView;

/**
 * Created by mateus on 25/01/18.
 */

public interface AddEditNoteContract {

    interface View extends BaseView<Presenter> {

        void showNotesList();

        void fillForm(String title, String description);

        void showLoadingNotesError();

        void hideDeleteOption();
    }

    interface Presenter extends BasePresenter {

        void saveNote(String title, String description);

        void deleteNote();
    }
}
