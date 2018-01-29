package com.example.mynotes.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

/**
 * Created by mateus on 24/01/18.
 */

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String description;

    public Note(int id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    @Ignore
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                title.equals(note.title) &&
                description.equals(note.description);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{id, title, description});
    }
}
