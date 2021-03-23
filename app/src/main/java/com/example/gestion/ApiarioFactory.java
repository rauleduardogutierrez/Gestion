package com.example.gestion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestion.models.ApiarioViewModel;

public class ApiarioFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    public ApiarioFactory (@NonNull Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == ApiarioViewModel.class){
            return (T) new ApiarioViewModel(application);
        }
        return null;
    }
}
