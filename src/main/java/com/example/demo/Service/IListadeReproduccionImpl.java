package com.example.demo.Service;

import com.example.demo.modelo.Entity.ListadeReproduccion;

import java.util.List;

public interface IListadeReproduccionImpl  {

    List<ListadeReproduccion> findAll();

    ListadeReproduccion save(ListadeReproduccion listaDeReproduccion);

    ListadeReproduccion findById(Long id);

    void delete(Long id);
}