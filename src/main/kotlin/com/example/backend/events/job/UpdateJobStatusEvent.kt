package com.example.backend.events.job

import com.example.backend.documents.JobStatus
import com.example.backend.events.Event
import com.example.backend.events.EventType
import java.io.Serial
import java.util.*


class UpdateJobStatusEvent(
    var jodId: UUID? = null,
    var newStatus: JobStatus? = null,
    var results: List<Any?>? = null,
) : Event() {

    @Serial
    val serialVersionUID: Long = 1285378575504850018L

    override fun getEventType(): EventType {
        return EventType.UPDATE_JOB_STATUS
    }
}