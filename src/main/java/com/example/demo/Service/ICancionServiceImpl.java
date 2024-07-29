package com.example.demo.Service;

import com.example.demo.modelo.Entity.Cancion;

import java.util.List;

public interface ICancionServiceImpl {
    List<Cancion> findAll();
    Cancion save(Cancion cancion);
    Cancion findById(Long id);
    void delete(Long id);





}