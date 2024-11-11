package com.example.backend.mapper;

import com.example.backend.domain.system.Job;
import com.example.backend.domain.system.JobResult;
import com.example.backend.dto.system.JobDto;
import com.example.backend.dto.system.JobResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobDto map(Job domain);

    JobResultDto map(JobResult domain);
}
