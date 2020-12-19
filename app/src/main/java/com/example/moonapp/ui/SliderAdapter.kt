package com.example.moonapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moonapp.databinding.ItemSliderTextBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val texts: List<String>) : SliderViewAdapter<SliderAdapter.SliderHolder>() {
    override fun getCount() = texts.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderHolder =
        SliderHolder(ItemSliderTextBinding.inflate(LayoutInflater.from(parent?.context)))

    override fun onBindViewHolder(viewHolder: SliderHolder, position: Int) =
        viewHolder.bind(texts[position])

    class SliderHolder(private val binder: ItemSliderTextBinding) :
        SliderViewAdapter.ViewHolder(binder.root) {
        fun bind(text: String) {
            binder.sliderText.text = text
        }
    }
}