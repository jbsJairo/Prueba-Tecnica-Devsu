package com.devsu.ms.cuentamovimiento.converter;

import org.springframework.core.convert.converter.Converter;

import com.devsu.ms.cuentamovimiento.dto.MovimientoDTO;
import com.devsu.ms.cuentamovimiento.model.response.MovimientoResponse;

public class MovimientoToMovimientoResponse implements Converter<MovimientoDTO, MovimientoResponse>{
	 @Override
	    public MovimientoResponse convert(MovimientoDTO source) {
	        return MovimientoResponse.builder()
	            .id(source.getId())
	            .cuentaId(source.getCuenta().getId())
	            .tipoMovimiento(source.getTipoMovimiento().name())
	            .fecha(source.getFecha())
	            .valor(source.getValor())
	            .saldo(source.getSaldo())
	        .build();
	    }
}
