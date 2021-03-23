package com.example.gestion;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gestion.daos.ApiarioDao;
import com.example.gestion.database.AppDatabaseApi;
import com.example.gestion.entities.Apiario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ApiarioTest {

    private ApiarioDao apiarioDao;
    private AppDatabaseApi appDatabaseApi;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        appDatabaseApi = Room.inMemoryDatabaseBuilder(context, AppDatabaseApi.class).build();
        apiarioDao = appDatabaseApi.apiarioDao();
    }
    @After
    public void closeDb() throws IOException {
        appDatabaseApi.close();
    }
    @Test
    public void  findByEstablecimientoTest() throws Exception {
        Apiario apiario = new Apiario();
        apiario.setId(1);
        apiario.setEstablecimiento("naranja");

        apiarioDao.insert(apiario);

        Apiario buscada = apiarioDao.findByEstablecimiento("naranja");

        assertTrue("No encontro la naranja que tenia", apiario.getId() == buscada.getId());
    }

}
