package com.mazaady.example

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mazaady.task.adapters.CategoriesAdapter
import com.mazaady.task.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val adapter = CategoriesAdapter(arrayListOf(),context","")
      //  binding.categoriesRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.categories.collect { categories ->
          //      adapter.submitList(categories)
            }
        }

        lifecycleScope.launch {
            viewModel.loading.collect { loading ->
                binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadCategories()
    }
}