package com.mazaady.task.presentation.fragments.auctionDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazaady.task.adapters.BiddersAdapter
import com.mazaady.task.adapters.ProductAdapter
import com.mazaady.task.databinding.FragmentAuctionDetailsBinding
import com.mazaady.task.presentation.fragments.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuctionFragment : Fragment() {


    private lateinit var binding: FragmentAuctionDetailsBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var biddersAdapter : BiddersAdapter
    private lateinit var productAdapters : ProductAdapter

    // private lateinit var mainRepo :MainRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.mazaady.task.R.layout.fragment_auction_details, container, false)

     //   initialization()
      //  observeInsertionState()


        setupBiddersRecyclerView()

        return binding.root
    }

    private fun setupBiddersRecyclerView()
    {
        binding.biddersRecyclerView.layoutManager=LinearLayoutManager(context)
        biddersAdapter = context?.let { BiddersAdapter(it) }!!
        binding.biddersRecyclerView.adapter =biddersAdapter

        binding.productsRecycle.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        productAdapters = context?.let { ProductAdapter(it) }!!
        binding.productsRecycle.adapter =productAdapters

    }



}