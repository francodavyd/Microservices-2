package com.francodavyd.repository;

import com.francodavyd.model.Vivienda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "vivienda-sv", url = "http://localhost:8020/vivienda")
public interface IViviendaFeignClient {

    @GetMapping("/obtenerPorId/{id}")
    public Vivienda obtenerPorId(@PathVariable ("id") Long id);
    @PutMapping("/agregarBarrio/{nombre}/{id}")
    public void agregarBarrio(@PathVariable ("nombre") String nombre, @PathVariable ("id") Long id);
}
