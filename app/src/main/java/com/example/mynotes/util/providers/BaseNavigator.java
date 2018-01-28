package com.example.mynotes.util.providers;

/**
 * Created by mateus on 27/01/18.
 */

public interface BaseNavigator {

    void startActivityForResult(Class cls, int requestCode);

    void startActivityForResultWithExtra(Class cls, int requestCode, String extraKey,
            int extraValue);

    void finishActivity();
}
