package com.example.mynotes.util.providers;

import android.app.Activity;
import android.content.Intent;

import java.lang.ref.WeakReference;

/**
 * Created by mateus on 27/01/18.
 */

public class Navigator implements BaseNavigator {

    private final WeakReference<Activity> activity;

    public Navigator(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public void startActivityForResult(Class cls, int requestCode) {
        if (activity.get() != null) {
            Intent intent = new Intent(activity.get(), cls);
            activity.get().startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void startActivityForResultWithExtra(Class cls, int requestCode, String extraKey,
            int extraValue) {
        if (activity.get() != null) {
            Intent intent = new Intent(activity.get(), cls);
            intent.putExtra(extraKey, extraValue);
            activity.get().startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void finishActivity() {
        if (activity.get() != null) {
            activity.get().finish();
        }
    }
}
