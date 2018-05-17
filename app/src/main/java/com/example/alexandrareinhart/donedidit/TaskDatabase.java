package com.example.alexandrareinhart.donedidit;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
}
