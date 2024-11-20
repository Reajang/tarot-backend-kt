package com.example.backend.repository;

import com.example.backend.documents.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends MongoRepository<Job, UUID> {
}
