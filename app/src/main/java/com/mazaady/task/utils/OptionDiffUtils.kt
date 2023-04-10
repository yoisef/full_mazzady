package com.mazaady.task.utils

import androidx.recyclerview.widget.DiffUtil
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData

class OptionDiffUtils(private val mOldDayList: List<Option>, private val mNewDayList: List<Option>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldDayList.size
    }

    override fun getNewListSize(): Int {
        return mNewDayList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldDayList[oldItemPosition].id === mNewDayList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldone = mOldDayList[oldItemPosition]
        val newone = mNewDayList[newItemPosition]
        return oldone == newone
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}