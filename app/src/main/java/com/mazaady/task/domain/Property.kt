package com.mazaady.task.domain

import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData

interface Property {

    fun onPropertyClick(propDetails : PropData)

    fun onOptionClick(option :Option, position: Int)

    fun getOptionChild(option :Option,parent : Int)
}