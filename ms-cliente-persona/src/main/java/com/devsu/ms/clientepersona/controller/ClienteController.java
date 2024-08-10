package com.devsu.ms.clientepersona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.ms.clientepersona.dto.ClienteDTO;
import com.devsu.ms.clientepersona.dto.model.request.ClienteRequest;
import com.devsu.ms.clientepersona.dto.model.response.ClienteResponse;
import com.devsu.ms.clientepersona.service.ClienteService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
	@GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
		 try {
			 var clienteDTOs = clienteService.findAll();
			    if (clienteDTOs.isEmpty()) {
			    	return ResponseEntity.notFound().build();
			    }
			 return ResponseEntity.ok(clienteDTOs);
		 } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }
    }
	
	@GetMapping(value = "cliente/identificacion/{identificacion}")
    public ResponseEntity<ClienteResponse> clientePorIdentificacion(@PathVariable String identificacion) {
		 try {
			 var clienteResponse = clienteService.findByIdentificacion(identificacion);
			 return ResponseEntity.ok(clienteResponse);
		 } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }
    }
	
	@GetMapping(value = "cliente/id/{id}")
    public ResponseEntity<ClienteResponse> clientePorId(@PathVariable Long id) {
		 try {
			 var clienteResponse = clienteService.findById(id);
			 return ResponseEntity.ok(clienteResponse);
		 } catch (EntityNotFoundException e) {
             return ResponseEntity.notFound().build();
         } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }			 
    }
	
	@PostMapping(value = "cliente/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest clienteRequest) {
		 try {
			 var clienteResponse = clienteService.save(clienteRequest);
			 return ResponseEntity.ok(clienteResponse);
		 } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }	
    }
	
    @PatchMapping(value = "cliente/actualizar/{id}")
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
    	 try {
    		 var clienteResponse = clienteService.update(id, clienteRequest);
    		 return ResponseEntity.ok(clienteResponse);
    	 } catch (EntityNotFoundException e) {
             return ResponseEntity.notFound().build();
         } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }
    }
	
    @DeleteMapping(value = "cliente/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
    	try {
	        clienteService.delete(id);
	        return ResponseEntity.ok(id + " Eliminado correctamente");
    	 } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }
    }
}
