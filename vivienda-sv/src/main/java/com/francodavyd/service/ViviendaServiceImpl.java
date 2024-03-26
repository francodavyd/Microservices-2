package com.francodavyd.service;

import com.francodavyd.dto.ViviendaDTO;
import com.francodavyd.model.Vivienda;
import com.francodavyd.repository.IViviendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaServiceImpl implements IViviendaService{
    @Autowired
    private IViviendaRepository repository;
    @Override
    public void crearVivienda(ViviendaDTO dto) {
        Vivienda vivienda = new Vivienda();
        vivienda.setTipoVivienda(dto.getTipoVivienda());
        vivienda.setHabitaciones(dto.getHabitaciones());
        vivienda.setBarrio(null);
        repository.save(vivienda);
    }

    @Override
    public List<Vivienda> obtenerLista() {
        return repository.findAll();
    }

    @Override
    public Vivienda obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Vivienda editarVivienda(ViviendaDTO dto, Long id) {
        Vivienda vivienda = this.obtenerPorId(id);
        vivienda.setTipoVivienda(dto.getTipoVivienda());
        vivienda.setHabitaciones(dto.getHabitaciones());

        repository.save(vivienda);
        return vivienda;
    }

    @Override
    public void agregarBarrio(String nombre, Long id) {
        Vivienda vivienda = this.obtenerPorId(id);
        vivienda.setBarrio(nombre);
        repository.save(vivienda);
    }
}
