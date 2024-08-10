package com.devsu.ms.clientepersona.dto.model;

import com.devsu.ms.clientepersona.dto.enums.EnumGenero;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class PersonaModel {
  
  @Column(nullable = false, length = 100)
  private String identificacion;
  
  @Column(nullable = false, length = 100)
  private String nombre;
  
  @Column(nullable = false, length = 1)
  @Enumerated(EnumType.STRING)
  private EnumGenero genero;
  
  private Integer edad;
  	
  @Column(length = 255)
  private String direccion;
  
  @Column(length = 15)
  private String telefono;
}
