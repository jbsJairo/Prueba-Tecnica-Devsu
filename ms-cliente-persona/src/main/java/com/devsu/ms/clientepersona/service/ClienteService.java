package com.devsu.ms.clientepersona.service;

import java.util.List;

import com.devsu.ms.clientepersona.dto.ClienteDTO;
import com.devsu.ms.clientepersona.dto.model.request.ClienteRequest;
import com.devsu.ms.clientepersona.dto.model.response.ClienteResponse;

public interface ClienteService {

	 public List<ClienteDTO> findAll();
	 public ClienteResponse findById(Long id);
	 public ClienteResponse findByIdentificacion(String identificacion);
	 public ClienteResponse save(ClienteRequest clienteRequest);
	 public ClienteResponse update(Long id, ClienteRequest clienteRequest);
	 public void delete(Long id);
}
