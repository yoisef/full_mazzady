package com.mazaady.task.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mazaady.task.R
import com.mazaady.task.databinding.RowProperty1Binding
import com.mazaady.task.databinding.RowPropertyBinding
import com.mazaady.task.databinding.RowPropertyValuesBinding
import com.mazaady.task.domain.Property
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData

import com.mazaady.task.utils.UserDiffCallback
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.properties.Delegates


class Property2Adapter @Inject constructor (var properties: ArrayList<PropData>, var context1: Context) :  RecyclerView.Adapter<Property2Adapter.ViewHolder>() {


    private lateinit var binding: RowPropertyValuesBinding
    private var context: Context = context1
    private var lastOptionId = 0
    private  var lastOption: Option?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = RowPropertyValuesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("selectedopion", "==" + (properties[position].selectedOption?.slug))
        val city = properties[position]
        holder.bind(city)


    }





    inner class ViewHolder(private val binding: RowPropertyValuesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(propertyDetails: PropData) {

            binding.propertyName.text = propertyDetails.slug
            binding.propertyValue.text= propertyDetails.selectedOption?.slug





        }


    }
}






