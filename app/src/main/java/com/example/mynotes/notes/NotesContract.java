package com.example.mynotes.notes;

import com.example.mynotes.BasePresenter;
import com.example.mynotes.BaseView;

/**
 * Created by mateus on 23/01/18.
 */

public interface NotesContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
