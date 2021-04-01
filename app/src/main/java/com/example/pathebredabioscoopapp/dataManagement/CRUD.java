package com.example.pathebredabioscoopapp.dataManagement;

import java.util.List;

public interface CRUD<T> {
    public void create(T params);
    public List<T> get();
    public void update(int id, String params);
    public void delete(int id);
}
