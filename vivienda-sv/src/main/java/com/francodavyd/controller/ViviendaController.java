package com.francodavyd.controller;

import com.francodavyd.dto.ViviendaDTO;
import com.francodavyd.model.Vivienda;
import com.francodavyd.service.IViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vivienda")
public class ViviendaController {
    @Autowired
    private IViviendaService service;
    @PostMapping("/crear")
    public ResponseEntity<?> crearVivienda(@RequestBody ViviendaDTO dto){
        try {
           service.crearVivienda(dto);
           return new ResponseEntity<>("Vivienda creada correctamente", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Ha ocurrido un error, vuelve a intentarlo", HttpStatus.BAD_REQUEST);
        }
    }
     @GetMapping("/obtenerLista")
    public ResponseEntity<?> obtenerLista(){
        try {
            List<Vivienda> list = service.obtenerLista();
            if (list == null){
                throw new Exception("No se encontraron viviendas registradas");
            }
            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            Vivienda vivienda = service.obtenerPorId(id);
            if (vivienda == null){
                throw new Exception("No se encontraron viviendas registradas con el ID " + id);
            }
            return new ResponseEntity<>(vivienda, HttpStatus.OK);
        } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try {
            Vivienda vivienda = service.obtenerPorId(id);
            if (vivienda == null){
                throw new Exception("No se encontraron viviendas registradas con el ID " + id);
            }
            service.eliminarPorId(id);
            return new ResponseEntity<>("Vivienda eliminada correctamente", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarVivienda(@RequestBody ViviendaDTO dto, @PathVariable Long id){
        try {
            Vivienda vivienda = service.obtenerPorId(id);
            if (vivienda == null){
                throw new Exception("No se encontraron viviendas registradas con el ID " + id);
            }
            return new ResponseEntity<>(service.editarVivienda(dto,id), HttpStatus.OK);
        } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/agregarBarrio/{nombre}/{id}")
    public void agregarBarrio(@PathVariable String nombre, @PathVariable Long id){
        service.agregarBarrio(nombre, id);
    }
}
