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
import androidx.recyclerview.widget.RecyclerView
import com.mazaady.task.R
import com.mazaady.task.databinding.RowBidderBinding
import com.mazaady.task.databinding.RowPropertyBinding
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.presentation.fragments.main.MainViewModel
import com.mazaady.task.utils.Status
import com.mazaady.task.utils.UserDiffCallback
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class BiddersAdapter @Inject constructor ( var context1: Context) :  RecyclerView.Adapter<BiddersAdapter.SearchViewHolder>() {


    private lateinit var binding: RowBidderBinding
    private var context: Context = context1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        binding = RowBidderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)

    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {


    }





    inner class SearchViewHolder(private val binding: RowBidderBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }
}



