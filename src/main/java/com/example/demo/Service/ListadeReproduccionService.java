package com.example.demo.Service;

import com.example.demo.modelo.Dao.IListadeReproduccionDao;
import com.example.demo.modelo.Entity.ListadeReproduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListadeReproduccionService implements IListadeReproduccionImpl {

    @Autowired
    private IListadeReproduccionDao listaDeReproduccionDao;

    @Override
    @Transactional(readOnly = true)
    public List<ListadeReproduccion> findAll() {
        return (List<ListadeReproduccion>) listaDeReproduccionDao.findAll();
    }

    @Override
    @Transactional
    public ListadeReproduccion save(ListadeReproduccion listaDeReproduccion) {
        return listaDeReproduccionDao.save(listaDeReproduccion);
    }

    @Override
    @Transactional(readOnly = true)
    public ListadeReproduccion findById(Long id) {
        return listaDeReproduccionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        listaDeReproduccionDao.deleteById(id);
    }
}