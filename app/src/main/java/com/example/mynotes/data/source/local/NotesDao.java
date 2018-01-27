package com.example.mynotes.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mynotes.data.Note;

import java.util.List;

/**
 * Created by mateus on 25/01/18.
 */

@Dao
public interface NotesDao {

    @Insert
    void insert(Note note);

    @Query("DELETE FROM notes WHERE id = :noteId")
    int deleteById(int noteId);

    @Update
    void update(Note note);

    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Query("SELECT * FROM notes where id = :noteId")
    Note getById(int noteId);
}
