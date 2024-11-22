package com.example.backend.mapper

import com.example.backend.documents.Job
import com.example.backend.documents.JobResult
import com.example.backend.dto.system.JobDto
import com.example.backend.dto.system.JobResultDto
import org.mapstruct.Mapper

@Mapper
interface JobMapper {

    fun map(domain: Job?): JobDto?
    fun map(domain: JobResult?): JobResultDto?
}