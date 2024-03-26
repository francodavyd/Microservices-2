package com.francodavyd.repository;

import com.francodavyd.model.Barrio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "barrio-sv", url = "http://localhost:8010/barrio")
public interface IBarrioFeignClient {
    @GetMapping("/obtenerPorId/{id}")
    public Barrio obtenerPorId(@PathVariable Long id);
    @PutMapping("/agregarDuenio/{nombre}/{id}")
    public void agregarDuenio(@PathVariable String nombre, @PathVariable Long id);
}
