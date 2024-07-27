package com.pi.rowadnotesapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pi.rowadnotesapp.database.dao.NoteDao;
import com.pi.rowadnotesapp.database.enities.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase myDataBase;

    public static MyDataBase getInstance(Context context) {
        if (myDataBase == null) {
            myDataBase = Room.databaseBuilder(context, MyDataBase.class, "notes_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return myDataBase;
    }

    public abstract NoteDao getNotesDao();

}
