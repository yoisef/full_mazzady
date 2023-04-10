package com.mazaady.task.domain.models

data class Option(
    val child: Boolean,
    val id: Int,
    val name: String,
    val parent: Int,
    var slug: String,
){
    override fun toString(): String =  this.slug;
}