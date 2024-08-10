package com.devsu.ms.cuentamovimiento.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsu.ms.cuentamovimiento.dto.MovimientoDTO;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoDTO, Long>{

    @Query("SELECT m, c FROM MovimientoDTO m LEFT JOIN m.cuenta c  " +
            " WHERE c.clienteId = ?1 AND m.fecha between ?2 AND ?3")
        List<MovimientoDTO> findByClienteAndFecha(Long clienteId, LocalDateTime fechaStart, LocalDateTime fechaEnd, Sort sort);
}
