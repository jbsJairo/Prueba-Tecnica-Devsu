package com.devsu.ms.cuentamovimiento.model.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponse {

	private Long id;
    private ClienteResponse cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoIni;
    private String estado;
}
