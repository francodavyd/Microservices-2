package com.francodavyd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Duenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Email
    private String email;
    private String telefono;
    @ElementCollection
    @CollectionTable(name = "lista_barrios", joinColumns = @JoinColumn(name = "id_duenio"))
    @Column(name = "id_barrio")
    private List<Barrio> listaBarrios = new ArrayList<>();

}
