package com.mazaady.task.domain.models

import android.util.Log
import android.widget.ArrayAdapter
import com.mazaady.task.R

data class Children(
    val children: Any,
    val circle_icon: String,
    val description: String,
    val disable_shipping: Int,
    val id: Int,
    val image: String,
    val name: String,
    val slug: String
){
    override fun toString(): String =  this.slug;


}