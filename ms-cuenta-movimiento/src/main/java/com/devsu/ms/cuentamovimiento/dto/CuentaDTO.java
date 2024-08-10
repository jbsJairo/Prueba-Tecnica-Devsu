package com.devsu.ms.cuentamovimiento.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.devsu.ms.cuentamovimiento.enums.EnumTipoCuenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "cuenta", schema = "public")
public class CuentaDTO {

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuenta_id_seq")
	  @SequenceGenerator(name = "cuenta_id_seq", sequenceName = "cuenta_id_seq", allocationSize = 1)
	  private Long id;
	  
	  @Column(unique = true, length = 15, nullable = false)
	  private String numeroCuenta;
	    
	  @Enumerated(EnumType.STRING)
	  private EnumTipoCuenta tipoCuenta;
	  
	  private BigDecimal saldoIni;
	    
	  @Column(nullable = false)
	  private Boolean estado;
	  
	  @Column(nullable = false)
	  private Long clienteId;
	  
	  @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
	  private Set<MovimientoDTO> movimientos;
}
