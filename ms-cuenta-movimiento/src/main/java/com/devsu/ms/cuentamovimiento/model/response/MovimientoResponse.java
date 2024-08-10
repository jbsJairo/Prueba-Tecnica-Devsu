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
public class MovimientoResponse {
	
	private Long id;
    private Long cuentaId;
    private String tipoMovimiento;
    private LocalDateTime fecha;
    private BigDecimal valor;
    private BigDecimal saldo;
}
