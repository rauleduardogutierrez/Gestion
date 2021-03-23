package com.example.gestion.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.gestion.daos.ApiarioDao;
import com.example.gestion.entities.Apiario;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Apiario.class}, version = 4, exportSchema = false)
public abstract class AppDatabaseApi extends RoomDatabase {

    public  abstract ApiarioDao apiarioDao();

    private static volatile AppDatabaseApi instance;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabaseApi getInstance( final Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseApi.class, "apiario")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
