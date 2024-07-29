package com.example.demo.Service;

import com.example.demo.modelo.Dao.ICancionDao;
import com.example.demo.modelo.Entity.Cancion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CancionService implements ICancionServiceImpl {

    @Autowired
    private ICancionDao cancionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cancion> findAll() {
        return (List<Cancion>) cancionDao.findAll();
    }

    @Override
    @Transactional
    public Cancion save(Cancion cancion) {
        return cancionDao.save(cancion);
    }

    @Override
    @Transactional(readOnly = true)
    public Cancion findById(Long id) {
        return cancionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cancionDao.deleteById(id);
    }
}