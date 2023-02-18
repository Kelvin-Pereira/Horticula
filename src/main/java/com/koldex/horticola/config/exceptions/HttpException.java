package com.koldex.horticola.config.exceptions;

public final class HttpException extends RuntimeException {
    public final int statusCode;

    /**
     * @param statusCode 4xx ou 5xx
     */
    public HttpException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static HttpException autenticacaoRequerida() {
        return new HttpException("AUTENTICAO_REQUERIDA", 401);
    }

    public static HttpException privilegiosInsuficientes() {
        return new HttpException("PRIVILEGIOS_INSUFICIENTES", 403);
    }

}
