package com.example.mynotes.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mynotes.data.Note;

/**
 * Created by mateus on 25/01/18.
 */

@Database(entities={Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "Notes.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract NotesDao getNotesDao();
}
