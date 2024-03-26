package com.francodavyd.service;

import com.francodavyd.dto.DuenioBarrioDTO;
import com.francodavyd.dto.DuenioDTO;
import com.francodavyd.model.Barrio;
import com.francodavyd.model.Duenio;

import java.util.List;

public interface IDuenioService {
    public void crearDuenio(DuenioDTO dto);
    public List<Duenio> obtenerDuenios();
    public Duenio obtenerPorId(Long id);
    public void eliminarPorId(Long id);
    public Duenio editarDuenio(DuenioDTO dto, Long id);
    public void agregarBarrio(DuenioBarrioDTO dto);
    public List<Barrio> listaBarrios(Long id);
}
