package com.devsu.ms.cuentamovimiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.ms.cuentamovimiento.dto.CuentaDTO;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaDTO, Long>{

    Optional<CuentaDTO> findByNumeroCuenta(String numeroCuenta);
}
