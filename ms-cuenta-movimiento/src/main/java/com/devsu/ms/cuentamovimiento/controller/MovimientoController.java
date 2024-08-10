package com.devsu.ms.cuentamovimiento.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.ms.cuentamovimiento.exception.CustomException;
import com.devsu.ms.cuentamovimiento.exception.ResponseException;
import com.devsu.ms.cuentamovimiento.model.resquest.RegistraMovimientoRequest;
import com.devsu.ms.cuentamovimiento.service.MovimientoService;

@RestController
@RequestMapping(path = "/movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovimientoController {
	
    @Autowired
    private MovimientoService movimientoService;

    
	@PostMapping(value = "movimiento/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registroMovimiento(@RequestBody RegistraMovimientoRequest registraMovimientoRequest) {
		 try {
			 var movimientoResponse = movimientoService.registroMovimiento(registraMovimientoRequest);
			 return ResponseEntity.ok(movimientoResponse);
		 } catch (CustomException e) {
			 ResponseException response = new ResponseException();
			 response.setCode(e.getStatus());
			 response.setMensaje(e.getMessage());
             return ResponseEntity.internalServerError().body(response);
         }	
    }
	
    @GetMapping(value="/reporte")
    public ResponseEntity<?> reporte(@RequestParam Long clienteId,
                                     @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaIni,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFin){
    	 try {
    		 return ResponseEntity.ok(this.movimientoService.estadoCuentaClienteFecha(clienteId, fechaIni, fechaFin));
    	 } catch (CustomException e) {
			 ResponseException response = new ResponseException();
			 response.setCode(e.getStatus());
			 response.setMensaje(e.getMessage());
             return ResponseEntity.internalServerError().body(response);
         }	
    }
}
