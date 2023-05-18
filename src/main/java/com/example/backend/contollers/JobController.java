package com.example.backend.contollers;

import com.example.backend.dto.system.JobDto;
import com.example.backend.service.JobService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@AllArgsConstructor
public class JobController {

    private final JobService service;

    @GetMapping("/get/{jobId}")
    public JobDto getJob(@PathVariable UUID jobId) {
        return service.get(jobId);
    }
}
