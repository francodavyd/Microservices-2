package com.francodavyd.service;

import com.francodavyd.dto.BarrioDTO;
import com.francodavyd.dto.BarrioViviendaDTO;
import com.francodavyd.model.Barrio;
import com.francodavyd.model.Vivienda;
import com.francodavyd.repository.IBarrioRepository;
import com.francodavyd.repository.IViviendaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BarrioServiceImpl implements IBarrioService{
    @Autowired
    private IBarrioRepository repository;
    @Autowired
    private IViviendaFeignClient feign;
    @Override
    public void crearBarrio(BarrioDTO dto) {
        Barrio barrio = new Barrio();
        barrio.setDireccion(dto.getDireccion());
        barrio.setNombre(dto.getNombre());
        barrio.setMetrosCuadrados(dto.getMetrosCuadrados() + " m2");
        barrio.setDuenio(null);
        repository.save(barrio);
    }

    @Override
    public List<Barrio> obtenerLista() {
        return repository.findAll();
    }

    @Override
    public Barrio obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }
    @Override
    public void agregarVivienda(BarrioViviendaDTO dto) {
        try {

            Vivienda vivienda = feign.obtenerPorId(dto.getIdVivienda());
            Barrio barrio = this.obtenerPorId(dto.getIdBarrio());

            if (vivienda != null && barrio != null) {
                List<Vivienda> list = barrio.getListaViviendas();
                list.add(vivienda);
                barrio.setListaViviendas(list);
                feign.agregarBarrio(barrio.getNombre(), dto.getIdVivienda());
                repository.save(barrio);
            } else {
                throw new Exception("Los datos ingresados son inválidos");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Vivienda> listaViviendas(Long id) {
        Barrio barrio = this.obtenerPorId(id);
        return barrio.getListaViviendas();
    }
    @Override
    public void agregarDuenio(String nombre, Long id) {
        Barrio barrio = this.obtenerPorId(id);
        barrio.setDuenio(nombre);
        repository.save(barrio);
    }
}
