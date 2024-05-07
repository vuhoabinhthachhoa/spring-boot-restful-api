package com.javaweb.utils.customExceptions;

public class DistrictNotFoundException extends RuntimeException {
    public DistrictNotFoundException(String message) {
        super(message);
    }
}