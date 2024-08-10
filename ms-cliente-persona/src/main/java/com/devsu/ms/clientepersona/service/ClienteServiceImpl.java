package com.devsu.ms.clientepersona.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.devsu.ms.clientepersona.dto.ClienteDTO;
import com.devsu.ms.clientepersona.dto.model.request.ClienteRequest;
import com.devsu.ms.clientepersona.dto.model.response.ClienteResponse;
import com.devsu.ms.clientepersona.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    
    private final ConversionService conversionService;
    
	@Override
	public List<ClienteDTO> findAll() {
		try {
			return clienteRepository.findAll();
		}catch (Exception e) {
		 	throw e;
		}
	}
	
    @Override
    public ClienteResponse findById(Long id) {
    	try {
            var clienteDTO = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
            return conversionService.convert(clienteDTO, ClienteResponse.class);
    	}catch (Exception e) {
    		 throw e;
		}
    }
    
    @Override
    public ClienteResponse findByIdentificacion(String identificacion) {
    	try {
            var clienteDTO = clienteRepository.findByIdentificacion(identificacion).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con la identificacion: " + identificacion));;
            return conversionService.convert(clienteDTO, ClienteResponse.class);
    	}catch (Exception e) {
    		 throw e;
		}
    }

	@Override
    @Transactional
	public ClienteResponse save(ClienteRequest clienteRequest) {
		try {
			 var clienteDTO = ClienteDTO.builder()
			            .nombre(clienteRequest.getNombre())
			            .genero(clienteRequest.getGenero())
			            .edad(clienteRequest.getEdad())
			            .identificacion(clienteRequest.getIdentificacion())
			            .direccion(clienteRequest.getDireccion())
			            .telefono(clienteRequest.getTelefono())
			            .password(clienteRequest.getPassword())
			            .estado(clienteRequest.isEstado())
			            .build();
			 this.clienteRepository.save(clienteDTO);
			 return  conversionService.convert(clienteDTO, ClienteResponse.class);
		}catch (Exception e) {
   		 throw e;
		}		 
	}
	
    @Override
    @Transactional
    public ClienteResponse update(Long id, ClienteRequest clienteRequest) {
		try {
	        var clienteDTO = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
	        actualizarCampos(clienteDTO, clienteRequest);
	        this.clienteRepository.save(clienteDTO);
	        return conversionService.convert(clienteDTO, ClienteResponse.class);
		}catch (Exception e) {
	   		 throw e;
		}
    }
	
    @Override
    @Transactional
    public void delete(Long id) {
    	try {
			clienteRepository.deleteById(id);
    	}catch (Exception e) {
      		 throw e;
   		}	        
    }
    
    private void actualizarCampos(ClienteDTO clienteDTO, ClienteRequest clienteRequest) {
    	clienteDTO.setNombre(Objects.requireNonNullElse(clienteRequest.getNombre(), clienteDTO.getNombre()));
    	clienteDTO.setGenero(Objects.requireNonNullElse(clienteRequest.getGenero(), clienteDTO.getGenero()));
    	clienteDTO.setEdad(Objects.requireNonNullElse(clienteRequest.getEdad(), clienteDTO.getEdad()));
    	clienteDTO.setIdentificacion(Objects.requireNonNullElse(clienteRequest.getIdentificacion(), clienteDTO.getIdentificacion()));
    	clienteDTO.setDireccion(Objects.requireNonNullElse(clienteRequest.getDireccion(), clienteDTO.getDireccion()));
    	clienteDTO.setTelefono(Objects.requireNonNullElse(clienteRequest.getTelefono(), clienteDTO.getTelefono()));
    	clienteDTO.setPassword(Objects.requireNonNullElse(clienteRequest.getPassword(), clienteDTO.getPassword()));
    	clienteDTO.setEstado(Objects.requireNonNullElse(clienteRequest.isEstado(), clienteDTO.getEstado()));
    	
    }
}
