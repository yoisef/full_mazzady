package com.mazaady.task.domain.models

data class Category(
    val children: List<Category>?,
    val circle_icon: String,
    val description: String,
    val disable_shipping: Int,
    val id: Int,
    val image: String,
    val name: String,
    val slug: String,


){

    override fun toString(): String =  this.slug;
}

