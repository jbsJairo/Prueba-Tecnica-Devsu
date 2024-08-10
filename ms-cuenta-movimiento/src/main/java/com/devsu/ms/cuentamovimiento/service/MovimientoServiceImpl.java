package com.devsu.ms.cuentamovimiento.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.ms.cuentamovimiento.client.ClienteClient;
import com.devsu.ms.cuentamovimiento.dto.MovimientoDTO;
import com.devsu.ms.cuentamovimiento.enums.EnumTipoCuenta;
import com.devsu.ms.cuentamovimiento.enums.EnumTipoMovimiento;
import com.devsu.ms.cuentamovimiento.exception.CustomException;
import com.devsu.ms.cuentamovimiento.model.response.MovimientoResponse;
import com.devsu.ms.cuentamovimiento.model.response.ReporteMovimientoResponse;
import com.devsu.ms.cuentamovimiento.model.resquest.MovimientoRequest;
import com.devsu.ms.cuentamovimiento.model.resquest.RegistraMovimientoRequest;
import com.devsu.ms.cuentamovimiento.repository.CuentaRepository;
import com.devsu.ms.cuentamovimiento.repository.MovimientoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private final CuentaRepository cuentaRepository;
    @Autowired
    private ClienteClient clienteClient;
    
    private final ConversionService conversionService;
    
	@Override
	public List<MovimientoDTO> findAll() {
		try {
			return movimientoRepository.findAll();
		}catch (Exception e) {
		 	throw e;
		}
	}
	
    @Override
    public MovimientoResponse findById(Long id) {
    	try {
            var movimientoDTO = movimientoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
            return conversionService.convert(movimientoDTO, MovimientoResponse.class);
    	}catch (Exception e) {
    		 throw e;
		}
    }
    
	@Override
    @Transactional
	public MovimientoResponse registroMovimiento(RegistraMovimientoRequest registraMovimientoRequest) {
		try {
			
			var cuentaDTO = cuentaRepository.findByNumeroCuenta(registraMovimientoRequest.getCuenta()).orElseThrow();
			
			 var valorMovimiento = registraMovimientoRequest.getValor();
		        if (cuentaDTO.getSaldoIni().add(valorMovimiento).compareTo(BigDecimal.ZERO) < 0) {
		        	throw new CustomException("Su salado Insuficiente para realizar esta Transaccion.", HttpStatus.NOT_ACCEPTABLE.value());
		        }
		     
		     int signo = tipoMovimiento(valorMovimiento).equals(EnumTipoMovimiento.I)?1:-1;
		        
			 var movimientoDTO = MovimientoDTO.builder()
			            .cuenta(cuentaDTO)
			            .tipoMovimiento(this.tipoMovimiento(valorMovimiento))
			            .fecha(LocalDateTime.now(ZoneId.systemDefault()))
			            .valor(valorMovimiento)
			            .saldo(cuentaDTO.getSaldoIni().add(valorMovimiento.abs().multiply(new BigDecimal(signo))))
			            .build();
			 
			 cuentaDTO.setSaldoIni(movimientoDTO.getSaldo());
			 cuentaRepository.save(cuentaDTO);
			 this.movimientoRepository.save(movimientoDTO);
			 
			 return  conversionService.convert(movimientoDTO, MovimientoResponse.class);
		}catch (Exception e) {
   		 throw e;
		}		 
	}
	
    private EnumTipoMovimiento tipoMovimiento(BigDecimal valorMovimiento) {
    	  if (valorMovimiento.compareTo(BigDecimal.ZERO) < 0) {
              return EnumTipoMovimiento.E;
          } else  {
              return EnumTipoMovimiento.I;
          } 
    }
    
	@Override
    @Transactional
	public MovimientoResponse save(MovimientoRequest movimientoRequest) {
		try {
			 var movimientoDTO = MovimientoDTO.builder()
			            .cuenta(movimientoRequest.getCuenta())
			            .tipoMovimiento(movimientoRequest.getTipoMovimiento())
			            .fecha(movimientoRequest.getFecha())
			            .valor(movimientoRequest.getValor())
			            .saldo(movimientoRequest.getSaldo())
			            .build();
			 this.movimientoRepository.save(movimientoDTO);
			 return  conversionService.convert(movimientoDTO, MovimientoResponse.class);
		}catch (Exception e) {
   		 throw e;
		}		 
	}
	
    @Override
    @Transactional
    public MovimientoResponse update(Long id, MovimientoRequest movimientoRequest) {
		try {
	        var movimientoDTO = movimientoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
	        actualizarCampos(movimientoDTO, movimientoRequest);
	        this.movimientoRepository.save(movimientoDTO);
	        return conversionService.convert(movimientoDTO, MovimientoResponse.class);
		}catch (Exception e) {
	   		 throw e;
		}
    }
    
    private void actualizarCampos(MovimientoDTO movimientoDTO, MovimientoRequest movimientoRequest) {
    	movimientoDTO.setCuenta(Objects.requireNonNullElse(movimientoRequest.getCuenta(), movimientoDTO.getCuenta()));
    	movimientoDTO.setTipoMovimiento(Objects.requireNonNullElse(movimientoRequest.getTipoMovimiento(), movimientoDTO.getTipoMovimiento()));
    	movimientoDTO.setFecha(Objects.requireNonNullElse(movimientoRequest.getFecha(), movimientoDTO.getFecha()));
    	movimientoDTO.setValor(Objects.requireNonNullElse(movimientoRequest.getValor(), movimientoDTO.getValor()));
    	movimientoDTO.setSaldo(Objects.requireNonNullElse(movimientoRequest.getSaldo(), movimientoDTO.getSaldo()));
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<ReporteMovimientoResponse> estadoCuentaClienteFecha(Long clienteId, LocalDate fechaIni, LocalDate fechaFin) {

        var fechaDia = LocalDate.now(ZoneId.systemDefault());
        var fechaConsultaFin =  Objects.requireNonNullElse(fechaFin, fechaDia);
        if (fechaIni.isAfter(fechaDia) || fechaConsultaFin.isAfter(fechaDia)) {
            throw new CustomException("La fecha de consulta no puede ser mayor a la fecha del dia", HttpStatus.BAD_REQUEST.value());
        }
        if (fechaConsultaFin.isBefore(fechaIni)) {
            throw new CustomException("La fecha de fin de la consulta no puede ser menor a la fecha de inicio", HttpStatus.BAD_REQUEST.value());
        }

        var fechaIniTemp = fechaIni.atStartOfDay();
        var fechaFinTemp = fechaFin.atTime(23,59,59);
        var movimientos = this.movimientoRepository.findByClienteAndFecha(clienteId, fechaIniTemp, fechaFinTemp, Sort.by("fecha").descending());
          
        var cliente = clienteClient.clientePorId(clienteId);

        return movimientos.stream().map(movimiento -> ReporteMovimientoResponse.builder()
            .fecha(movimiento.getFecha())
            .cliente(cliente.getBody().getNombre())
            .cuenta(movimiento.getCuenta().getNumeroCuenta())
            .tipoCuenta(movimiento.getCuenta().getTipoCuenta().equals(EnumTipoCuenta.A)?"AHORRO":"CORRIENTE")
            .estado(movimiento.getCuenta().getEstado().toString())
            .movimiento(movimiento.getValor())
            .saldo(movimiento.getSaldo())
            .build()).toList();
    }
}
