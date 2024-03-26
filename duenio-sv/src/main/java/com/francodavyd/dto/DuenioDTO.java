package com.francodavyd.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class DuenioDTO {
    private String nombre;
    private String apellido;
    @Email
    private String email;
    private String telefono;
}
