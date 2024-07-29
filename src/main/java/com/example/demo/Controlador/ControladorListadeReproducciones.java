package com.example.demo.Controlador;

import com.example.demo.modelo.Entity.Cancion;
import com.example.demo.modelo.Entity.ListadeReproduccion;
import com.example.demo.Service.ICancionServiceImpl;
import com.example.demo.Service.IListadeReproduccionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/listas-reproduccion")

@CrossOrigin(origins = "*")
public class ControladorListadeReproducciones {


    @Autowired
    private IListadeReproduccionImpl listaDeReproduccionService;

    @Autowired
    private ICancionServiceImpl cancionService;

    @GetMapping
    public ResponseEntity<Iterable<ListadeReproduccion>> obtenerTodas() {
        Iterable<ListadeReproduccion> listas = listaDeReproduccionService.findAll();
        return ResponseEntity.ok(listas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadeReproduccion> obtenerPorId(@PathVariable Long id) {
        ListadeReproduccion lista = listaDeReproduccionService.findById(id);
        if (lista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ListadeReproduccion> crearListaDeReproduccion(@RequestBody ListadeReproduccion listaDeReproduccion) {
        if (listaDeReproduccion.getName() == null || listaDeReproduccion.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ListadeReproduccion nuevaLista = listaDeReproduccionService.save(listaDeReproduccion);

        if (nuevaLista != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevaLista.getId())
                    .toUri();
            return ResponseEntity.created(location).body(nuevaLista);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListadeReproduccion> actualizarListaDeReproduccion(@PathVariable Long id, @RequestBody ListadeReproduccion listaDeReproduccion) {
        listaDeReproduccion.setId(id);
        ListadeReproduccion actualizada = listaDeReproduccionService.save(listaDeReproduccion);
        return ResponseEntity.ok(actualizada);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarListaDeReproduccion(@PathVariable Long id) {
        ListadeReproduccion listaExistente = listaDeReproduccionService.findById(id);
        if (listaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        listaDeReproduccionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}