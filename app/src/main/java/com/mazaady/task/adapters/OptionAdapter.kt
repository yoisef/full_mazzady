package com.mazaady.task.adapters

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mazaady.task.R
import com.mazaady.task.databinding.RowOptionBinding
import com.mazaady.task.databinding.RowPropertyBinding
import com.mazaady.task.domain.Property
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.presentation.fragments.main.MainViewModel
import com.mazaady.task.utils.OptionDiffUtils
import com.mazaady.task.utils.Status
import com.mazaady.task.utils.UserDiffCallback
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.Collections.addAll
import javax.inject.Inject


class OptionAdapter @Inject constructor (var options: ArrayList<Option>, var context1: Context, actions:Property) :  RecyclerView.Adapter<OptionAdapter.ViewHolder>() {


    private lateinit var binding: RowOptionBinding
    private var context: Context = context1
    private val actions:Property = actions



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = RowOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = options.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = options[position]
        holder.bind(option)


    }


    fun updateOptionListItems(newOptions: List<Option>) {


        val diffCallback = OptionDiffUtils(this.options, newOptions)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.options.clear()
        this.options.addAll(newOptions)
        diffResult.dispatchUpdatesTo(this)




    }





    inner class ViewHolder(private val binding: RowOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(optionDetails: Option) {
            binding.option = optionDetails

            binding.propertyLayout.setOnClickListener {


                actions.onOptionClick(optionDetails,adapterPosition)

            }



            }


        }





}



