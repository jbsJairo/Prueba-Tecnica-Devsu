package com.devsu.ms.clientepersona.converter;

import org.springframework.core.convert.converter.Converter;
import com.devsu.ms.clientepersona.dto.ClienteDTO;
import com.devsu.ms.clientepersona.dto.model.response.ClienteResponse;

public class ClienteToClienteResponse implements Converter<ClienteDTO, ClienteResponse>{
	 @Override
    public ClienteResponse convert(ClienteDTO source) {
        return ClienteResponse.builder()
            .id(source.getId())
            .nombre(source.getNombre())
            .genero(source.getGenero().name())
            .edad(source.getEdad())
            .identificacion(source.getIdentificacion())
            .direccion(source.getDireccion())
            .telefono(source.getTelefono())
            .estado(source.getEstado().toString())
        .build();
    }
}