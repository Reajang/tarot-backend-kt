package com.example.backend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String resourceName, String identifier) {
        super(String.format("Can not find resource \"%s\" by identifier \"%s\"", resourceName, identifier));
    }
}
