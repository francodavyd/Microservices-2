package com.francodavyd.service;

import com.francodavyd.dto.BarrioDTO;
import com.francodavyd.dto.BarrioViviendaDTO;
import com.francodavyd.model.Vivienda;
import com.francodavyd.model.Barrio;

import java.util.List;

public interface IBarrioService {
    public void crearBarrio(BarrioDTO dto);
    public List<Barrio> obtenerLista();
    public Barrio obtenerPorId(Long id);
    public void eliminarPorId(Long id);
    public void agregarVivienda(BarrioViviendaDTO dto);
    public List<Vivienda> listaViviendas(Long id);
    public void agregarDuenio(String nombre, Long id);
}
