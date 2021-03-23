package com.example.gestion.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.gestion.daos.ApiarioDao;
import com.example.gestion.database.AppDatabaseApi;
import com.example.gestion.entities.Apiario;

import java.util.List;

public class ApiarioRepository {

    private ApiarioDao apiarioDao;
    private LiveData<List<Apiario>> apiarios;

    private List<Apiario>  establecimientos;


    public ApiarioRepository(Application application){

        AppDatabaseApi db = AppDatabaseApi.getInstance(application);
        apiarioDao = db.apiarioDao();
        apiarios = apiarioDao.getAllapiarios();
    }
    public LiveData<List<Apiario>> getApiarios(){
        return apiarios;
    }

    public List<Apiario> getEstablecimientos(){return establecimientos;}

    public void insert(Apiario apiario) {
        AppDatabaseApi.databaseWriteExecutor.execute(() -> {

            apiarioDao.insert(apiario);

        });

    }

    public void update(Apiario apiario) {
        AppDatabaseApi.databaseWriteExecutor.execute(() -> {

            apiarioDao.update(apiario);

        });
    }
    public void delete(Apiario apiario) {
        AppDatabaseApi.databaseWriteExecutor.execute(() -> {

            apiarioDao.delete(apiario);

        });
    }
}
