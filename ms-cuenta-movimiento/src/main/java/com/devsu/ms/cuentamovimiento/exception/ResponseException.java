package com.devsu.ms.cuentamovimiento.exception;

import lombok.Data;

@Data
public class ResponseException {

	private Object code;
	private String mensaje;
}
