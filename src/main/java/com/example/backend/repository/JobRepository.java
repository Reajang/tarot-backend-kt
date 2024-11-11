package com.example.backend.repository;

import com.example.backend.domain.system.Job;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, UUID> {
}
