package com.devsu.ms.clientepersona.dto.model.request;

import com.devsu.ms.clientepersona.dto.enums.EnumGenero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    private String nombre;
    private EnumGenero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String password;
    private boolean estado;
}
