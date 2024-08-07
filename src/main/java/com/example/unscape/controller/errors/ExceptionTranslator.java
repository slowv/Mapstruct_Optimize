package com.example.unscape.controller.errors;

import com.example.unscape.common.constant.AppConstants;
import com.example.unscape.service.dto.response.ErrorResponse;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author slowv
 * @since 21/Oct/2020
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class ExceptionTranslator implements ResponseErrorHandler {
    private ResponseEntity<ErrorResponse> badRequest(ErrorResponse result) {
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> success(ErrorResponse result) {
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private ResponseEntity<ErrorResponse> internalServerError(ErrorResponse result) {
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> notFound(ErrorResponse result) {
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> forbidden(ErrorResponse result) {
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ErrorResponse> handleNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("service", ex.getMessage());
        return success(new ErrorResponse("405", "Method not allowed", map));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("service", ex.getMessage());
        return success(new ErrorResponse("400", "File upload must less than 5MB", map));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> bindException(BindException ex) {
        final Map<String, Object> errorResults = new HashMap<>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            if ("typeMismatch".contains(Objects.requireNonNull(e.getCode()))) {
                errorResults.put(e.getField(), "Inputted data is not in provided list");
                continue;
            }
            errorResults.put(e.getField(), e.getDefaultMessage());
        }
        final ErrorResponse response = new ErrorResponse(AppConstants.BAD_REQUEST.getCode(),
                "Validation exception", errorResults);
        return badRequest(response);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("service", ex.getMessage());
        return internalServerError(new ErrorResponse(AppConstants.SERVICE_ERROR.getCode(), "Service Error", map));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        final Map<String, Object> errorResults = new HashMap<>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            errorResults.put(e.getField(), e.getDefaultMessage());
        }
        final ErrorResponse response = new ErrorResponse(AppConstants.BAD_REQUEST.getCode(),
                "Validation exception", errorResults);
        return badRequest(response);
    }


    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return success(new ErrorResponse(AppConstants.BAD_REQUEST.getCode(), ex.getMessage()));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleValidationException() {
        return badRequest(new ErrorResponse(AppConstants.NOT_READABLE_REQUEST.getCode(), AppConstants.NOT_READABLE_REQUEST.getMessage()));
    }


    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    ResponseEntity<ErrorResponse> handleAccessException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("security", e.getMessage());
        return forbidden(new ErrorResponse(AppConstants.FORBIDDEN.getCode(), AppConstants.FORBIDDEN.getMessage(), map));
    }


    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatusCode status = clientHttpResponse.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String responseAsString = toString(response.getBody());
        throw new CustomException(responseAsString);
    }

    @Override
    public void handleError(URI url,
                            HttpMethod method,
                            ClientHttpResponse response) throws IOException {
        String responseAsString = toString(response.getBody());
        throw new CustomException(responseAsString);
    }

    private String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        log.info("Controller advice - message: {}", ex.getMessage());
        return internalServerError(new ErrorResponse(AppConstants.SERVICE_ERROR.getCode(), "Service Error"));
    }

}
