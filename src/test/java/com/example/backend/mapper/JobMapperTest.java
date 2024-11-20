package com.example.backend.mapper;

public class JobMapperTest {

//    @Test
//    void mapToDtoTest() {
//        Job job = Job.builder()
//            .id(UUID.randomUUID())
//            .createDate(Instant.now())
//            .updateDate(Instant.now())
//            .status(JobStatus.RUNNING)
//            .type(JobType.TAROT_FUTURE_TELL)
//            .build();
//        JobResult jobResult = JobResult.builder()
//            .id(UUID.randomUUID())
//            .createDate(Instant.now())
//            .updateDate(Instant.now())
//            .data("data")
//            .type("type")
//            .job(job)
//            .build();
//        job.setResults(List.of(jobResult));
//
//        JobDto dto = assertDoesNotThrow(() -> JobMapper.INSTANCE.map(job));
//
//        assertNotNull(dto);
//        assertEquals(job.getId(), dto.id());
//        assertEquals(job.getCreateDate(), dto.createDate());
//        assertEquals(job.getUpdateDate(), dto.updateDate());
//        assertEquals(job.getType(), dto.type());
//        assertEquals(job.getStatus(), dto.status());
//        assertEquals(job.getResults().get(0).getData(), dto.results().get(0).data());
//        assertEquals(job.getResults().get(0).getType(), dto.results().get(0).type());
//    }
}
