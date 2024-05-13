package com.dev.sukran.jwtspringsecurity.ErrorHandling;

import com.dev.sukran.jwtspringsecurity.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler { @ExceptionHandler(Exception.class)
public final ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponseDto error = new ErrorResponseDto("Server Error", HttpStatus.INTERNAL_SERVER_ERROR.toString(), details);
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
}

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponseDto> handleUserNotFoundException(RecordNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseDto error = new ErrorResponseDto("Record Not Found", HttpStatus.NOT_FOUND.toString(), details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpClientErrorException(HttpClientErrorException ex) {
        List<String> details = new ArrayList<>();
        details.add("Response body: " + ex.getResponseBodyAsString());
        ErrorResponseDto error = new ErrorResponseDto("Client Error", ex.getStatusCode().toString(), details);
        return new ResponseEntity(error, ex.getStatusCode());
    }


}
