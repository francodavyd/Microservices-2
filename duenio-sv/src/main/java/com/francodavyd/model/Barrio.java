package com.francodavyd.model;


import lombok.Getter;

import java.io.Serializable;

@Getter
public class Barrio implements Serializable {
    private Long id;
    private String direccion;
    private String nombre;
}
