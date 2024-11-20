package com.example.backend.dto.common

import com.example.backend.lang.Language

interface Translatable {
    fun from(): Language?
    fun to(): Language?
    fun text(): String?
}