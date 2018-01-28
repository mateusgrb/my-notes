package com.example.mynotes.addeditnote;

import android.support.annotation.NonNull;

import com.example.mynotes.util.providers.BaseNavigator;

/**
 * Created by mateus on 27/01/18.
 */

public class AddEditNoteNavigator {

    private final BaseNavigator provider;

    public AddEditNoteNavigator(@NonNull BaseNavigator navigationProvider) {
        provider = navigationProvider;
    }

    public void onNoteUpdated() {
        provider.finishActivity();
    }
}
