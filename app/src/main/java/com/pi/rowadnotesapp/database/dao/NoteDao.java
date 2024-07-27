package com.pi.rowadnotesapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pi.rowadnotesapp.database.enities.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void add(Note note);

    @Query("select * from Note")
    public List<Note> getAllNotes();
}
