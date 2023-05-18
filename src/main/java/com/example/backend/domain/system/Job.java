package com.example.backend.domain.system;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "udpate_date")
    private Instant updateDate;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private JobType type;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private JobStatus status;

    @PreUpdate
    @PrePersist
    public void preSave() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }

        Instant now = Instant.now();
        if (this.createDate == null) {
            this.createDate = now;
        }
        this.updateDate = now;
    }

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
