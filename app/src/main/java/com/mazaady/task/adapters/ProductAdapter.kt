package com.mazaady.task.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.mazaady.task.databinding.RowProductBinding
import javax.inject.Inject


class ProductAdapter @Inject constructor (var context1: Context) :  RecyclerView.Adapter<ProductAdapter.SearchViewHolder>() {


    private lateinit var binding: RowProductBinding
    private var context: Context = context1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        binding = RowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)

    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
      //  val city = days[position]
     //   holder.bind(city)


    }





    inner class SearchViewHolder(private val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }
}



