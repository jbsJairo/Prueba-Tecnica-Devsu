package com.devsu.ms.cuentamovimiento.model.resquest;

import java.math.BigDecimal;

import com.devsu.ms.cuentamovimiento.enums.EnumTipoCuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaRequest {
	
    private ClienteRequest cliente;
    private String numeroCuenta;
    private EnumTipoCuenta tipoCuenta;
    private BigDecimal saldoIni;
    private boolean estado;
}
