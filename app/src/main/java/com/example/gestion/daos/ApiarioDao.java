package com.example.gestion.daos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gestion.entities.Apiario;

import java.util.List;

@Dao
public interface  ApiarioDao {


    @Query("SELECT * FROM apiario")
    LiveData<List<Apiario>> getAllapiarios();

    @Insert
    void insert(Apiario apiario);

    @Update
    void update(Apiario apiario);

    @Delete
    void delete(Apiario apiario);

    @Query("SELECT * from apiario where establecimiento LIKE :establecimiento")
    List<Apiario> findByEstablecimiento(String establecimiento);



    @Query("SELECT * from apiario where id = :id")
    Apiario findById(int id);


}
