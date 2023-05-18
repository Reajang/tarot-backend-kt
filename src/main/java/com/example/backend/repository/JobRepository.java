package com.example.backend.repository;

import com.example.backend.domain.system.Job;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, UUID> {
}
