package org.kras.redismultithreading.exception;

import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<String> handleValidationException(GlobalException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Problem happened failed: " + ex.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Processing failed: " + ex.getMessage());
    }

    @ExceptionHandler(SerializationException.class)
    public ResponseEntity<String> handleSerializationException(SerializationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Processing failed: " + ex.getMessage());
    }
    //TODO expend if more custom exceptions are necessary
}
