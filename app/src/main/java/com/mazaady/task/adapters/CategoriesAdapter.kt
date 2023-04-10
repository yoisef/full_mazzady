package com.mazaady.task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mazaady.task.databinding.RowCategoryBinding
import com.mazaady.task.domain.ICategory
import com.mazaady.task.domain.models.Category
import com.mazaady.task.utils.CategoryDiffUtils

import javax.inject.Inject


class CategoriesAdapter @Inject constructor (var options: ArrayList<Category>, var context1: Context, actions:ICategory) :  RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


    private lateinit var binding: RowCategoryBinding
    private var context: Context = context1
    private val actions:ICategory = actions



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = RowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = options.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = options[position]
        holder.bind(option)


    }


    fun updateOptionListItems(newCategories: List<Category>) {


        val diffCallback = CategoryDiffUtils(this.options, newCategories)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.options.clear()
        this.options.addAll(newCategories)
        diffResult.dispatchUpdatesTo(this)




    }







    inner class ViewHolder(private val binding: RowCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryDetails: Category) {
            binding.category = categoryDetails

            binding.propertyLayout.setOnClickListener {


                if (categoryDetails.children!=null)
                {
                    actions.onCategorySubClick(categoryDetails,1)

                }else{
                    actions.onCategorySubClick(categoryDetails,2)

                }


            }



            }


        }





}



