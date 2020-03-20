package com.mobi.efficacious.demoproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = Note.class,version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

     
}
