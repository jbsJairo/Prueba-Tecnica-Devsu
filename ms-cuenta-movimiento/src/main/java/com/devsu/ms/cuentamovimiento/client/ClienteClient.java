package com.devsu.ms.cuentamovimiento.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.devsu.ms.cuentamovimiento.model.response.ClienteResponse;
import com.devsu.ms.cuentamovimiento.model.resquest.ClienteRequest;


@FeignClient(name = "clientepersona", url = "localhost:8080/clientes")
public interface ClienteClient {

	@PostMapping(value = "cliente/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest clienteModel);
	
	@GetMapping(value = "cliente/identificacion/{identificacion}")
	public ResponseEntity<ClienteResponse> clientePorIdentificacion(@PathVariable String identificacion);
	
	@GetMapping(value = "cliente/id/{id}")
    public ResponseEntity<ClienteResponse> clientePorId(@PathVariable Long id);
}
