package com.example.backend.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFound(resourceName: String, identifier: String) :
    RuntimeException("Can not find resource \"$resourceName\" by identifier \"%$identifier\"")
