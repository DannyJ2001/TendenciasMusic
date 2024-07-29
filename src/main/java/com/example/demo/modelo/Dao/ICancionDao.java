package com.example.demo.modelo.Dao;

import com.example.demo.modelo.Entity.Cancion;
import org.springframework.data.repository.CrudRepository;

public interface ICancionDao extends CrudRepository<Cancion, Long> {
}