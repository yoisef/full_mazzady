package com.mazaady.task.domain.models

data class PropData(
    val description: String,
    val id: Int,
    val list: Boolean,
    val name: String,
    val options: MutableList<Option>,
    var childOptions: MutableList<Option>?=null,

    var selectedOption: Option?=null,

    val other_value: Any,
    val parent: Int,
    val slug: String,
    val type: String,
    val value: String
){

        override fun toString(): String =  this.slug;

}