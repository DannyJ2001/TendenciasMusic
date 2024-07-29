package com.example.demo.Controlador;


import com.example.demo.Service.ICancionServiceImpl;
import com.example.demo.Service.IListadeReproduccionImpl;
import com.example.demo.modelo.Entity.Cancion;
import com.example.demo.modelo.Entity.ListadeReproduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/canciones")
@CrossOrigin(origins = "*")
public class CancionController {

    @Autowired
    private ICancionServiceImpl cancionService;

    @Autowired
    private IListadeReproduccionImpl listaDeReproduccionService;

    @GetMapping
    public ResponseEntity<Iterable<Cancion>> obtenerTodas() {
        Iterable<Cancion> canciones = cancionService.findAll();
        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerPorId(@PathVariable Long id) {
        Cancion cancion = cancionService.findById(id);
        if (cancion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cancion);
    }

    @PostMapping
    public ResponseEntity<Cancion> crearCancion(@RequestBody Cancion cancion) {
        // Validar la existencia de la lista de reproducción
        if (cancion.getPlaylist() == null || cancion.getPlaylist().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        ListadeReproduccion listaExistente = listaDeReproduccionService.findById(cancion.getPlaylist().getId());
        if (listaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        cancion.setPlaylist(listaExistente);
        Cancion nuevaCancion = cancionService.save(cancion);

        if (nuevaCancion != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevaCancion.getId())
                    .toUri();
            return ResponseEntity.created(location).body(nuevaCancion);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> actualizarCancion(@PathVariable Long id, @RequestBody Cancion cancion) {
        Cancion existente = cancionService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        cancion.setId(id);
        Cancion actualizada = cancionService.save(cancion);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Long id) {
        Cancion cancionExistente = cancionService.findById(id);
        if (cancionExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        cancionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
