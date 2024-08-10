package com.devsu.ms.clientepersona.dto;

import com.devsu.ms.clientepersona.dto.model.PersonaModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "cliente", schema = "public")
public class ClienteDTO extends PersonaModel {
	
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq")
  @SequenceGenerator(name = "cliente_id_seq", sequenceName = "cliente_id_seq", allocationSize = 1)
  private Long id;
  
  @Column(nullable = false, length = 255)
  private String password;
  
  @Column(nullable = false)
  private Boolean estado;
}
