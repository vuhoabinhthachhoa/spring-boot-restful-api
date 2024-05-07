package com.javaweb.utils.customExceptions;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(String message) {
        super(message);
    }
}