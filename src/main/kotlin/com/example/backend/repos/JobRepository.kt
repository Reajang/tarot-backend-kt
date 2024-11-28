package com.example.backend.repos

import com.example.backend.documents.Job
import com.example.backend.documents.JobStatus
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface JobRepository : ReactiveMongoRepository<Job, UUID> {

    @Query("{'_id':  ?0}")
    @Update("{'status':  ?1}")
    fun updateJobStatus(jobId: UUID, jobStatus: JobStatus?)
}