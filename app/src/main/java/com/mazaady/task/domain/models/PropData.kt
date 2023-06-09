package com.mazaady.task.domain.models

data class PropData(
    val description: String,
    val id: Int,
    val list: Boolean,
    val name: String,
    var options: MutableList<Option>,
    var childOptions: MutableList<Option>?=null,

    var selectedOption: Option?=null,

    var other_value: String,
    val parent: Int,
    val slug: String,
    val type: String,
    val value: String
){

        override fun toString(): String =  this.slug;

}