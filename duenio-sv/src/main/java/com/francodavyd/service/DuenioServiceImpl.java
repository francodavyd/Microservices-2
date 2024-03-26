package com.francodavyd.service;

import com.francodavyd.dto.DuenioBarrioDTO;
import com.francodavyd.dto.DuenioDTO;
import com.francodavyd.model.Barrio;
import com.francodavyd.model.Duenio;
import com.francodavyd.repository.IBarrioFeignClient;
import com.francodavyd.repository.IDuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenioServiceImpl implements IDuenioService {
    @Autowired
    private IDuenioRepository repository;
    @Autowired
    private IBarrioFeignClient feign;
    @Override
    public void crearDuenio(DuenioDTO dto) {
        Duenio duenio = new Duenio();
        duenio.setNombre(dto.getNombre());
        duenio.setApellido(dto.getApellido());
        duenio.setEmail(dto.getEmail());
        duenio.setTelefono(dto.getTelefono());
        repository.save(duenio);
    }

    @Override
    public List<Duenio> obtenerDuenios() {
        return repository.findAll();
    }

    @Override
    public Duenio obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Duenio editarDuenio(DuenioDTO dto, Long id) {
        return null;
    }

    @Override
    public void agregarBarrio(DuenioBarrioDTO dto) {
     try {
         Barrio barrio = feign.obtenerPorId(dto.getIdBarrio());
         Duenio duenio = this.obtenerPorId(dto.getIdDuenio());

         if (barrio != null && duenio != null){
             List<Barrio> list = duenio.getListaBarrios();
             list.add(barrio);
             duenio.setListaBarrios(list);
             feign.agregarDuenio(duenio.getNombre() + " " + duenio.getApellido(), dto.getIdBarrio());
             repository.save(duenio);
         } else {
             throw new Exception("Los datos ingresados son inválidos");
         }
     } catch (Exception e){
         e.printStackTrace();
     }
    }

    @Override
    public List<Barrio> listaBarrios(Long id) {
        Duenio duenio = this.obtenerPorId(id);
        return duenio.getListaBarrios();
    }
}
