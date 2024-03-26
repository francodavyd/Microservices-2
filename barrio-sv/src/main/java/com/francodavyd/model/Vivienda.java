package com.francodavyd.model;

import lombok.Getter;

import java.io.Serializable;


@Getter
public class Vivienda implements Serializable {
    private Long id;
    private String tipoVivienda;
    private int habitaciones;
}
