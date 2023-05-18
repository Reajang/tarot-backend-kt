package com.example.backend.domain.system;


import com.example.backend.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job_result")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResult extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "data")
    private String data;

//    @Column(name = "type")
//    @Enumerated(value = EnumType.STRING)
//    private JobResultType type;

    @Column(name = "type")
    private String type;


}
