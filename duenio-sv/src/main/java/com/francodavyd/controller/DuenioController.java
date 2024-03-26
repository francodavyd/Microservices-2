package com.francodavyd.controller;

import com.francodavyd.dto.DuenioBarrioDTO;
import com.francodavyd.dto.DuenioDTO;
import com.francodavyd.model.Barrio;
import com.francodavyd.model.Duenio;
import com.francodavyd.repository.IBarrioFeignClient;
import com.francodavyd.service.IDuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duenio")
public class DuenioController {
    @Autowired
    private IDuenioService service;
    @Autowired
    private IBarrioFeignClient feign;
    @PostMapping("/crear")
    public ResponseEntity<?> crearDuenio(@RequestBody DuenioDTO dto){
        try {
            service.crearDuenio(dto);
            return new ResponseEntity<>("Dueño registrado correctamente", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Ha ocurrido un error, vuelve a intentarlo", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerLista")
    public ResponseEntity<?> obtenerLista(){
        try {
            List<Duenio> list = service.obtenerDuenios();
            if (list == null){
                throw new Exception("No se han encontrado registros");
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            Duenio duenio = service.obtenerPorId(id);
            if (duenio == null){
                throw new Exception("No se encontraron registros con el ID " + id);
            }
            return new ResponseEntity<>(duenio, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try {
            Duenio duenio = service.obtenerPorId(id);
            if (duenio == null){
                throw new Exception("No se encontraron registros con el ID " + id);
            }
            service.eliminarPorId(id);
            return new ResponseEntity<>("Dueño eliminado correctamente", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/agregarBarrio")
    public ResponseEntity<?> agregarBarrio(@RequestBody DuenioBarrioDTO dto){
        try {
            Barrio barrio = feign.obtenerPorId(dto.getIdBarrio());
            Duenio duenio = service.obtenerPorId(dto.getIdDuenio());
            if (barrio == null){
                throw new Exception("El barrio solicitado no se ha encontrado");
            }
            if (duenio == null){
                throw new Exception("El registro solicitado no se ha encontrado");
            }
            service.agregarBarrio(dto);
            return new ResponseEntity<>("Barrio agregado correctamente al registro de " + duenio.getNombre() + " " + duenio.getApellido(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/obtenerBarrios/{idDuenio}")
    public ResponseEntity<?> obtenerListaBarrios(@PathVariable Long idDuenio){
        try {
            Duenio duenio = service.obtenerPorId(idDuenio);
            if (duenio == null){
                throw new Exception("El registro solicitado no se ha encontrado");
            }
            return new ResponseEntity<>(service.listaBarrios(idDuenio), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
