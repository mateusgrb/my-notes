package com.example.mynotes.data;

/**
 * Created by mateus on 24/01/18.
 */

public class Note {

    String title;

    String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
