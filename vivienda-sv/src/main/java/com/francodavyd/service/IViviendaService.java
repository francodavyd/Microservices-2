package com.francodavyd.service;

import com.francodavyd.dto.ViviendaDTO;
import com.francodavyd.model.Vivienda;

import java.util.List;

public interface IViviendaService {
    public void crearVivienda(ViviendaDTO dto);
    public List<Vivienda> obtenerLista();
    public Vivienda obtenerPorId(Long id);
    public void eliminarPorId(Long id);
    public Vivienda editarVivienda(ViviendaDTO dto, Long id);
    public void agregarBarrio(String nombre, Long id);
}
