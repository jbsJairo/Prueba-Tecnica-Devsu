package com.devsu.ms.cuentamovimiento.model.resquest;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistraMovimientoRequest {
	
    private String cuenta;
    private BigDecimal valor;
}
