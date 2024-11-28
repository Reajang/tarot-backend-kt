package com.example.backend.documents

import java.time.Instant
import java.util.*

data class JobResult(
    var id: UUID? = null,
    var createDate: Instant? = null,
    var data: String? = null,
    var type: String? = null
)