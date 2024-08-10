package com.devsu.ms.cuentamovimiento.model.resquest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.ms.cuentamovimiento.dto.CuentaDTO;
import com.devsu.ms.cuentamovimiento.enums.EnumTipoMovimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoRequest {
	
    private CuentaDTO cuenta;
    private EnumTipoMovimiento tipoMovimiento;
    private LocalDateTime fecha;
    private BigDecimal valor;
    private BigDecimal saldo;
}
