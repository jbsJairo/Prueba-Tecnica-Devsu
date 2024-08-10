package com.devsu.ms.cuentamovimiento.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.devsu.ms.cuentamovimiento.converter.CuentaToCuentaResponse;
import com.devsu.ms.cuentamovimiento.converter.MovimientoToMovimientoResponse;

@Configuration
public class AppConfig {

	@Bean
    ConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new CuentaToCuentaResponse());
        conversionService.addConverter(new MovimientoToMovimientoResponse());
        return conversionService;
    }
}
