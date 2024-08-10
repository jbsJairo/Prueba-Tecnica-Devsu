package com.devsu.ms.cuentamovimiento.converter;

import org.springframework.core.convert.converter.Converter;

import com.devsu.ms.cuentamovimiento.dto.CuentaDTO;
import com.devsu.ms.cuentamovimiento.model.response.CuentaResponse;

public class CuentaToCuentaResponse implements Converter<CuentaDTO, CuentaResponse>{
	 @Override
	    public CuentaResponse convert(CuentaDTO source) {
	        return CuentaResponse.builder()
	            .id(source.getId())
	            .numeroCuenta(source.getNumeroCuenta())
	            .tipoCuenta(source.getTipoCuenta().name())
	            .saldoIni(source.getSaldoIni())
	            .estado(source.getEstado().toString())
	        .build();
	    }
}
