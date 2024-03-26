package com.francodavyd.controller;

import com.francodavyd.dto.BarrioDTO;
import com.francodavyd.dto.BarrioViviendaDTO;
import com.francodavyd.model.Vivienda;
import com.francodavyd.model.Barrio;
import com.francodavyd.repository.IViviendaFeignClient;
import com.francodavyd.service.IBarrioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barrio")
public class BarrioController {
    @Autowired
    private IBarrioService service;
    @Autowired
    private IViviendaFeignClient feign;
    @PostMapping("/crear")
    public ResponseEntity<?> crearBarrio(@RequestBody BarrioDTO dto){
        try {
            service.crearBarrio(dto);
            return new ResponseEntity<>("Barrio registrado correctamente", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Ha ocurrido un error, vuelve a intentarlo", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerLista")
    public ResponseEntity<?> obtenerLista(){
        try {
            List<Barrio> list = service.obtenerLista();
            if (list == null){
                throw new Exception("No se han encontrado barrios registrados");
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            Barrio barrio = service.obtenerPorId(id);
            if (barrio == null){
                throw new Exception("No se encontraron barrios registrados con el ID " + id);
            }
            return new ResponseEntity<>(barrio, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try {
            Barrio barrio = service.obtenerPorId(id);
            if (barrio == null){
                throw new Exception("No se encontraron barrios registrados con el ID " + id);
            }
            service.eliminarPorId(id);
            return new ResponseEntity<>("Barrio eliminado correctamente", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
@CircuitBreaker(name = "viviendaCB", fallbackMethod = "fallbackagregarVivienda")
    @PostMapping("/agregarVivienda")
    public ResponseEntity<?> agregarVivienda(@RequestBody BarrioViviendaDTO dto){
        try {
            Vivienda vivienda = feign.obtenerPorId(dto.getIdVivienda());
            Barrio barrio = service.obtenerPorId(dto.getIdBarrio());
        if (vivienda == null){
            throw new Exception("La vivienda solicitada no se ha encontrado");
        }
        if (barrio == null){
            throw new Exception("El barrio solicitado no se ha encontrado");
        }
        service.agregarVivienda(dto);
        return new ResponseEntity<>("Vivienda agregada correctamente al barrio " + barrio.getNombre(), HttpStatus.OK);
        } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> fallbackAgregarVivienda(@RequestBody BarrioViviendaDTO dto){
        return new ResponseEntity<>("Ha ocurrido un error, vuelve a intentarlo", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/obtenerViviendas/{idBarrio}")
    public ResponseEntity<?> obtenerListaViviendas(@PathVariable Long idBarrio){
        try {
            Barrio barrio = service.obtenerPorId(idBarrio);
            if (barrio == null){
                throw new Exception("El barrio solicitado no se ha encontrado");
            }
            return new ResponseEntity<>(service.listaViviendas(idBarrio), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/agregarDuenio/{nombre}/{id}")
    public void agregarDuenio(@PathVariable String nombre, @PathVariable Long id){
        service.agregarDuenio(nombre,id);
    }
}
