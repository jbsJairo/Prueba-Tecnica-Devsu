package com.devsu.ms.cuentamovimiento.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int status;
    private final String mensaje;

    public CustomException(final String mensaje) {
        this(mensaje, (Throwable)null);
    }

    public CustomException(final String mensaje, final Throwable cause) {
        this(mensaje, 500, cause);
    }

    public CustomException(final String mensaje, final int httpStatus) {
        this(mensaje, httpStatus, (Throwable)null);
    }

    public CustomException(final String mensaje, final int httpStatus, final Throwable cause) {
        super(mensaje, cause);
        this.mensaje = mensaje;
        this.status = httpStatus;
    }
}
