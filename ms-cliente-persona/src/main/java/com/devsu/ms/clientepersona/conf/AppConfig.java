package com.devsu.ms.clientepersona.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.devsu.ms.clientepersona.converter.ClienteToClienteResponse;

@Configuration
public class AppConfig {

	@Bean
    ConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new ClienteToClienteResponse());
        return conversionService;
    }

}
