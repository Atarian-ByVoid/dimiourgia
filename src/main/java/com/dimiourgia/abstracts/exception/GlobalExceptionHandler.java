package com.dimiourgia.abstracts.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dimiourgia.external.ErrorDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, Object> response = new HashMap<>();
        Map<String, String> fields = new HashMap<>();

        BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                fields.put(error.getField(), error.getDefaultMessage());
            }
        }

        response.put("error", fields);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();

        InvalidFormatException invalidFormatEx = (InvalidFormatException) ex.getCause();
        String fieldName = invalidFormatEx.getPath().get(0).getFieldName();
        Class<?> targetClass = invalidFormatEx.getTargetType();
        String nomeTargetClass = targetClass.toString().substring(targetClass.toString().lastIndexOf('.') + 1);

        if (targetClass.isEnum()) {
            Object[] enumConstants = targetClass.getEnumConstants();
            Map<String, Object> fieldInfos = new HashMap<>();
            String expectedEnums = "";

            for (int i = 0; i < enumConstants.length; i++) {
                if (i == 0) {
                    expectedEnums = expectedEnums + enumConstants[i].toString();
                    continue;
                }
                expectedEnums = expectedEnums + "|" + enumConstants[i].toString();
            }

            fieldInfos.put("message", "Valor inválido: " + invalidFormatEx.getValue());
            fieldInfos.put("expected", expectedEnums);
            fields.put(fieldName, fieldInfos);

            response.put("error", fields);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (nomeTargetClass.equals("LocalDate")) {
            Map<String, Object> fieldInfos = new HashMap<>();
            fieldInfos.put("message", "Data em formato inválido");
            fieldInfos.put("expected", "yyyy/mm/dd");
            fields.put(fieldName, fieldInfos);

            response.put("error", fields);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (nomeTargetClass.contains("DTOValidation")) {
            Map<String, Object> fieldInfos = new HashMap<>();
            fieldInfos.put("message", "Deve ser um OBJETO JSON");
            fieldInfos.put("expected", "Objeto JSON {}");
            fields.put(fieldName, fieldInfos);

            response.put("error", fields);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> infoNaoMapeada = new HashMap<>();
        infoNaoMapeada.put("message", "Classe não mapeada para tratamento na exception");
        infoNaoMapeada.put("exception", "HttpMessageNotReadableException");
        infoNaoMapeada.put("class", nomeTargetClass);

        fields = infoNaoMapeada;

        response.put("error", fields);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientException.class)
    protected ResponseEntity<Object> handleWebClientException(
            WebClientException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Erro com WebClient: " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException() {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Acesso Negado");

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExistsException(
            EntityExistsException ex) {

        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleIOException(IOException ex) {

        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MsErrorException.class)
    protected ResponseEntity<ErrorDTO> handleMsErrorException(MsErrorException e) {
        return new ResponseEntity<>(new ErrorDTO(e), e.getStatusCode());
    }
}
