package com.javaweb.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javaweb.utils.customExceptions.BuildingNotFoundException;
import com.javaweb.utils.customExceptions.DistrictNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DistrictNotFoundException.class)
    public ResponseEntity<?> handleDistrictNotFoundException(DistrictNotFoundException ex) {
        // Create a custom error response
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        // Return the response entity
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(BuildingNotFoundException.class)
    public ResponseEntity<?> handleDistrictNotFoundException(BuildingNotFoundException ex) {
        // Create a custom error response
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        // Return the response entity
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }
}
