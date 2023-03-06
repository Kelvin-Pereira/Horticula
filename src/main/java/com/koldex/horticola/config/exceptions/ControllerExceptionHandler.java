package com.koldex.horticola.config.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleValidationExceptions(MethodArgumentNotValidException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription("Campos da requisição inválidos.");
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String errorMessage = e.getDefaultMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseError handleConstraintViolationException(ConstraintViolationException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription(ex.getMessage());
        ex.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath() == null ? "" : cv.getPropertyPath().toString();
            String errorMessage = cv.getMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NegocioException.class)
    public ResponseError handleNegocioExceptionException(NegocioException e) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription(e.getMessage());
        return responseError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNotFoundExceptions(NoSuchElementException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.NOT_FOUND.value());
        responseError.setDescription("Recurso não encontrado.");
        return responseError;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception e) {
        log.error(String.valueOf(e));
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setDescription("Ocorreu um erro interno em nosso servidor.");
        return responseError;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseError handleBadCredentialsException(BadCredentialsException e) {
        log.error(String.valueOf(e));
        ResponseError responseError = new ResponseError();
        responseError.setDescription("Credenciais inválidas");
        responseError.setCode(HttpStatus.UNAUTHORIZED.value());
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseError handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(String.valueOf(ex));
        ResponseError responseError = new ResponseError();
        responseError.setDescription("Erro de violação de integridade do banco de dados");
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseError handleDataIntegrityViolationException(HttpMessageConversionException ex) {
        log.error(String.valueOf(ex));
        ResponseError responseError = new ResponseError();
        responseError.setDescription(ex.getCause().getCause().getMessage());
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        return responseError;
    }

}
