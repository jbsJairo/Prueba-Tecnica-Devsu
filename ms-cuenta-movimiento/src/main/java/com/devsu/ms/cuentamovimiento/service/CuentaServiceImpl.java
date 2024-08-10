package com.devsu.ms.cuentamovimiento.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.devsu.ms.cuentamovimiento.client.ClienteClient;
import com.devsu.ms.cuentamovimiento.dto.CuentaDTO;
import com.devsu.ms.cuentamovimiento.model.response.CuentaResponse;
import com.devsu.ms.cuentamovimiento.model.resquest.CuentaRequest;
import com.devsu.ms.cuentamovimiento.repository.CuentaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {
	
    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
    private ClienteClient clienteClient;
    
    private final ConversionService conversionService;
    
	@Override
	public List<CuentaDTO> findAll() {
		try {
			return cuentaRepository.findAll();
		}catch (Exception e) {
		 	throw e;
		}
	}
	
    @Override
    public CuentaResponse findById(Long id) {
    	try {
            var cuentaDTO = cuentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
            return conversionService.convert(cuentaDTO, CuentaResponse.class);
    	}catch (Exception e) {
    		 throw e;
		}
    }
    
    
    @Override
    public CuentaResponse findByNumeroCuenta(String numeroCuenta) {
    	try {
            var cuentaDTO = cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con el nro. cuenta: " + numeroCuenta));
            return conversionService.convert(cuentaDTO, CuentaResponse.class);
    	}catch (Exception e) {
    		 throw e;
		}
    }
    
	@Override
    @Transactional
	public CuentaResponse save(CuentaRequest cuentaRequest) {
		try {
			
			var clienteResponse = clienteClient.crear(cuentaRequest.getCliente());
			
			 var cuentaDTO = CuentaDTO.builder()
			            .clienteId(clienteResponse.getBody().getId())
			            .numeroCuenta(cuentaRequest.getNumeroCuenta())
			            .tipoCuenta(cuentaRequest.getTipoCuenta())
			            .saldoIni(cuentaRequest.getSaldoIni())
			            .estado(cuentaRequest.isEstado())
			            .build();
			 this.cuentaRepository.save(cuentaDTO);
			 return CuentaResponse.builder()
					 .cliente(clienteResponse.getBody())
					 .id(cuentaDTO.getId())
			         .numeroCuenta(cuentaDTO.getNumeroCuenta())
			         .tipoCuenta(cuentaDTO.getTipoCuenta().name())
			         .saldoIni(cuentaDTO.getSaldoIni())
			         .estado(cuentaDTO.getEstado().toString())
					 .build();
		}catch (Exception e) {
   		 throw e;
		}		 
	}
	
    @Override
    @Transactional
    public CuentaResponse update(Long id, CuentaRequest cuentaRequest) {
		try {
	        var cuentaDTO = cuentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
	        actualizarCampos(cuentaDTO, cuentaRequest);
	        this.cuentaRepository.save(cuentaDTO);
	        return conversionService.convert(cuentaDTO, CuentaResponse.class);
		}catch (Exception e) {
	   		 throw e;
		}
    }
    
    private void actualizarCampos(CuentaDTO cuentaDTO, CuentaRequest cuentaRequest) {
    	cuentaDTO.setNumeroCuenta(Objects.requireNonNullElse(cuentaRequest.getNumeroCuenta(), cuentaDTO.getNumeroCuenta()));
    	cuentaDTO.setTipoCuenta(Objects.requireNonNullElse(cuentaRequest.getTipoCuenta(), cuentaDTO.getTipoCuenta()));
    	cuentaDTO.setSaldoIni(Objects.requireNonNullElse(cuentaRequest.getSaldoIni(), cuentaDTO.getSaldoIni()));
    	cuentaDTO.setEstado(Objects.requireNonNullElse(cuentaRequest.isEstado(), cuentaDTO.getEstado()));
    }
}
