package com.devsu.ms.cuentamovimiento.service;

import java.time.LocalDate;
import java.util.List;

import com.devsu.ms.cuentamovimiento.dto.MovimientoDTO;
import com.devsu.ms.cuentamovimiento.model.response.MovimientoResponse;
import com.devsu.ms.cuentamovimiento.model.response.ReporteMovimientoResponse;
import com.devsu.ms.cuentamovimiento.model.resquest.MovimientoRequest;
import com.devsu.ms.cuentamovimiento.model.resquest.RegistraMovimientoRequest;

public interface MovimientoService {
	
	public List<MovimientoDTO> findAll();
	public MovimientoResponse findById(Long id);
	public MovimientoResponse registroMovimiento(RegistraMovimientoRequest registraMovimientoRequest);
	public MovimientoResponse save(MovimientoRequest movimientoRequest);
	public MovimientoResponse update(Long id, MovimientoRequest movimientoRequest);
	public List<ReporteMovimientoResponse> estadoCuentaClienteFecha(Long clienteId, LocalDate fechaIni, LocalDate fechaFin);
}
