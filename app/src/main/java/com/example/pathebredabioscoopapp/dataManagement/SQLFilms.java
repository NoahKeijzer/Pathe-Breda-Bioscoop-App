package com.example.pathebredabioscoopapp.dataManagement;

import java.util.List;
import com.example.pathebredabioscoopapp.domain.Films;

public class SQLFilms implements CRUD<Films>{
    private final String TAG = getClass().getSimpleName();

    @Override
    public void create(Films params) {

    }

    @Override
    public List<Films> get() {
        return null;
    }

    @Override
    public void update(int id, String params) {

    }

    @Override
    public void delete(int id) {

    }
}
