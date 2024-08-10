package com.devsu.ms.cuentamovimiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.ms.cuentamovimiento.dto.CuentaDTO;
import com.devsu.ms.cuentamovimiento.model.response.CuentaResponse;
import com.devsu.ms.cuentamovimiento.model.resquest.CuentaRequest;
import com.devsu.ms.cuentamovimiento.service.CuentaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping
    public ResponseEntity<List<CuentaDTO>> listar() {
		 try {
			 var cuentaDTOs = cuentaService.findAll();
			    if (cuentaDTOs.isEmpty()) {
			    	return ResponseEntity.notFound().build();
			    }
			 return ResponseEntity.ok(cuentaDTOs);
		 } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }
    }
	
	@GetMapping(value = "cuenta/id/{id}")
    public ResponseEntity<CuentaResponse> cuenta(@PathVariable Long id) {
		 try {
			 var cuentaResponse = cuentaService.findById(id);
			 return ResponseEntity.ok(cuentaResponse);
		 } catch (EntityNotFoundException e) {
             return ResponseEntity.notFound().build();
         } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }			 
    }
	
	@PostMapping(value = "cuenta/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CuentaResponse> crear(@RequestBody CuentaRequest cuentaRequest) {
		 try {
			 var cuentaResponse = cuentaService.save(cuentaRequest);
			 return ResponseEntity.ok(cuentaResponse);
		 } catch (Exception e) {
             return ResponseEntity.internalServerError().body(null);
         }	
    }	
}
