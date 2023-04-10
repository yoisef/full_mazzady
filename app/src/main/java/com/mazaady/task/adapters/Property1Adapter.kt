package com.mazaady.task.adapters

import android.content.Context

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mazaady.task.databinding.RowProperty1Binding
import com.mazaady.task.databinding.RowPropertyBinding
import com.mazaady.task.domain.Property
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData

import com.mazaady.task.utils.UserDiffCallback
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.properties.Delegates


class Property1Adapter @Inject constructor (var properties: ArrayList<PropData>, var context1: Context, actions: Property) :  RecyclerView.Adapter<Property1Adapter.ViewHolder>() {


    private lateinit var binding: RowProperty1Binding
    private var context: Context = context1
    private val actions: Property = actions
    private var lastOptionId = 0
    private  var lastOption: Option?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = RowProperty1Binding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("selectedopion", "==" + (properties[position].selectedOption?.slug))
        val city = properties[position]
        holder.bind(city)


    }


    fun updateDayListItems(newDays: List<PropData>) {

        val diffCallback = UserDiffCallback(this.properties, newDays)
       val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.properties.clear()
        this.properties.addAll(newDays)
      //  notifyDataSetChanged()
       diffResult.dispatchUpdatesTo(this)



    }

    fun addNewProperty(property: PropData, position: Int) {
        this.properties.add(position, property)
        notifyItemInserted(position)

    }
    fun updateProperty(option: Option,position: Int)
    {
        this.properties.filter { prop -> prop.id==option.parent }.let {
            it[0].selectedOption=option
            val index = properties.indexOf(it[0])

            notifyItemChanged(index)
        }

    }


    inner class ViewHolder(private val binding: RowProperty1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(propertyDetails: PropData) {

            lastOption=propertyDetails.selectedOption
            binding.property = propertyDetails
            binding.inputLayoutProperty.hint = propertyDetails.slug
            binding.inputEditTextProperty.setOnClickListener {

                actions.onPropertyClick(propertyDetails)
            }



            if (propertyDetails.selectedOption != null) {

                Log.e(
                    "optionselected",
                    "selected" + propertyDetails.selectedOption!!.slug.toString()
                )
                Log.e("optionInAdapter","=="+ propertyDetails.selectedOption!!.slug.toString())

                  binding.inputEditTextProperty.setText(propertyDetails.selectedOption?.slug)


                if (propertyDetails.selectedOption!!.child) {


                    actions.getOptionChild(propertyDetails.selectedOption!!, adapterPosition)




                }


                if (propertyDetails.selectedOption!!.name.equals("other")) {
                    binding.inputLayoutOther.visibility = View.VISIBLE
                    binding.inputEditTextOther.requestFocus()

                    binding.inputEditTextOther.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            propertyDetails.selectedOption?.slug  =s.toString()
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                    })


                } else {
                    binding.inputLayoutOther.visibility = View.GONE

                }


            }else{

            }



        }


    }
}






