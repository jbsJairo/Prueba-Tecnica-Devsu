package com.devsu.ms.clientepersona.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.ms.clientepersona.dto.ClienteDTO;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDTO, Long> {

	Optional<ClienteDTO> findByIdentificacion(String identificacion);
}
