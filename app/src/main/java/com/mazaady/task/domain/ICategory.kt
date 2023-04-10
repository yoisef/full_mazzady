package com.mazaady.task.domain

import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData

interface ICategory {

    fun onCategorySubClick(propDetails : Category?, categoryOrSub: Int)


}