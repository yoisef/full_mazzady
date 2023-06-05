package com.mazaady.task.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mazaady.task.R
import com.mazaady.task.domain.models.SliderItem
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(context: Context) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private val context: Context
    private var mSliderItems: MutableList<SliderItem> = ArrayList()

    init {
        this.context = context
        mSliderItems.add(0, SliderItem(R.drawable.car2))
        mSliderItems.add(1,SliderItem(R.drawable.elantra))

    }

    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: SliderItem = mSliderItems[position]

        Glide.with(viewHolder.itemView)
            .load(sliderItem.image)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        lateinit var itemView: View
        var imageViewBackground: ImageView

        init {
            imageViewBackground = itemView.findViewById(R.id.image_car)

        }
    }
}