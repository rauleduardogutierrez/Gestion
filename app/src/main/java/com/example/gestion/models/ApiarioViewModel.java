package com.example.gestion.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gestion.entities.Apiario;
import com.example.gestion.repositories.ApiarioRepository;

import java.util.List;

public class ApiarioViewModel extends AndroidViewModel {

    private ApiarioRepository apiarioRepository;

    private final LiveData<List<Apiario>> apiarios;

    private Apiario  establecimientos;

    public ApiarioViewModel(Application application){
        super(application);

        apiarioRepository = new ApiarioRepository(application);
        apiarios = apiarioRepository.getApiarios();

    }
    public LiveData<List<Apiario>> getApiarios(){
        return apiarios;
    }

    public Apiario getEstablecimientos(){return establecimientos;}

    public void insert(Apiario apiario){

        apiarioRepository.insert(apiario);
    }
    public void update(Apiario apiario){

        apiarioRepository.update(apiario);
    }
    public  void delete(Apiario apiario){

        apiarioRepository.delete(apiario);
    }

}

