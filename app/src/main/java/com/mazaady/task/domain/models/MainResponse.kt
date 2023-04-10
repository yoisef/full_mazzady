package com.mazaady.task.domain.models

data class MainResponse<T>(
    val code: Int,
    val `data`: T,
    val msg: String
)