package com.example.backend.controllers

import com.example.backend.dto.system.JobDto
import com.example.backend.service.JobService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/job")
class JobController(private val service: JobService) {

    @GetMapping("/get/{jobId}")
    fun getJob(@PathVariable jobId: UUID?): JobDto? {
        return service.get(jobId!!)
    }
}