package com.example.backend.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    protected UUID id;

    @Basic(optional = false)
    @Column(name = "create_date", nullable = false)
    protected Instant createDate;

    @Column(name = "update_date")
    protected Instant updateDate;

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

}