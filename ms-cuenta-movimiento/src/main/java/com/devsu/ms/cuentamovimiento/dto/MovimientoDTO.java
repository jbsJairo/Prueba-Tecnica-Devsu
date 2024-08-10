package com.devsu.ms.cuentamovimiento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.ms.cuentamovimiento.enums.EnumTipoMovimiento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "movimiento", schema = "public")
public class MovimientoDTO {
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimiento_id_seq")
	  @SequenceGenerator(name = "movimiento_id_seq", sequenceName = "movimiento_id_seq", allocationSize = 1)
	  private Long id;
	    
	  @Enumerated(EnumType.STRING)
	  private EnumTipoMovimiento tipoMovimiento;
	  
	  @Temporal(TemporalType.TIMESTAMP)
	  private LocalDateTime fecha;
	    
	  private BigDecimal valor;
	  
	  private BigDecimal saldo;
	  
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="cuenta_id")
	  private CuentaDTO cuenta;
}
