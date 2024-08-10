package com.devsu.ms.cuentamovimiento.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteMovimientoResponse {

	private LocalDateTime fecha;
	private String cliente;
	private String cuenta;
	private String tipoCuenta;
	private String estado;
	private BigDecimal movimiento;
	private BigDecimal saldo;
	
}
