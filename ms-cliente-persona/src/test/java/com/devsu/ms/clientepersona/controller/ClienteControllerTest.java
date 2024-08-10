package com.devsu.ms.clientepersona.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsu.ms.clientepersona.repository.ClienteRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = {ClienteRepository.class,ClienteController.class})
public class ClienteControllerTest {

    @MockBean
    private ClienteRepository clienteRepository;
    @Autowired
    ClienteController clienteController;
    @Autowired
    private MockMvc mockMvc;
    
    
    @Test
    public void whenPostRequestToClientAndValidClient_thenCorrectResponse() throws Exception {
        String client = """
                {
				  "nombre": "ANITA SEGURA MENDEZ",
				  "genero": "F",
				  "edad": 63,
				  "identificacion": "0930276477",
				  "direccion": "PASCUALES",
				  "telefono": "0988765466",
				  "password": "123445", 
				  "estado": "TRUE"
                }""";
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes/crear")
                        .content(client)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }
}
