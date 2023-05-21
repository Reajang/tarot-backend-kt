package com.example.backend.domain.system;

import com.example.backend.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "job")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Job extends BaseEntity {

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private JobType type;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private JobStatus status;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<JobResult> results = new ArrayList<>();

    @Transient
    public static Job newIdle(JobType jobType) {
        Job newJob = new Job();
        newJob.setId(UUID.randomUUID());
        newJob.setCreateDate(Instant.now());
        newJob.setType(jobType);
        newJob.setStatus(JobStatus.IDLE);
        return newJob;
    }
}
