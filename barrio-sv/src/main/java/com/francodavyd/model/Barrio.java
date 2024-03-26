package com.francodavyd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private String nombre;
    @Column(name = "metros_cuadrados")
    private String metrosCuadrados;
    @ElementCollection
    @CollectionTable(name = "lista_viviendas", joinColumns = @JoinColumn(name = "id_barrio"))
    @Column(name = "id_vivienda")
    private List<Vivienda> listaViviendas = new ArrayList<>();
    @Column(name = "dueño")
    private String duenio;
}
