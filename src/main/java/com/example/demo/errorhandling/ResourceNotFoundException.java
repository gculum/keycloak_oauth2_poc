package com.example.demo.errorhandling;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Integer id) {
        super(String.format("Resource with Id %d not found", id));
    }

    public ResourceNotFoundException(Class clazz, Integer id) {
        super(String.format("Resource %s with Id %d not found", clazz.getName(), id));
    }
}