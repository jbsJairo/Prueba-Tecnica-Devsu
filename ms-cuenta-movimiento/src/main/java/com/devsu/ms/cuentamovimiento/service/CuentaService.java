package com.devsu.ms.cuentamovimiento.service;

import java.util.List;

import com.devsu.ms.cuentamovimiento.dto.CuentaDTO;
import com.devsu.ms.cuentamovimiento.model.response.CuentaResponse;
import com.devsu.ms.cuentamovimiento.model.resquest.CuentaRequest;

public interface CuentaService {

	public List<CuentaDTO> findAll();
	public CuentaResponse findById(Long id);
	public CuentaResponse findByNumeroCuenta(String numeroCuenta);
	public CuentaResponse save(CuentaRequest cuentaRequest);
	public CuentaResponse update(Long id, CuentaRequest cuentaRequest);
}
