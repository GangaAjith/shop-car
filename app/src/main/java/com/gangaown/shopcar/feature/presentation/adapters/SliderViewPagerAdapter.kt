package com.gangaown.shopcar.feature.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gangaown.shopcar.R
import com.gangaown.shopcar.feature.domain.model.ImageGallery
import com.google.android.material.imageview.ShapeableImageView

class SliderViewPagerAdapter(private var mImageList:List<ImageGallery>):
    RecyclerView.Adapter<SliderViewPagerAdapter.SliderImageHolder>() {

    inner class SliderImageHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val sliderImageView = itemView.findViewById<ShapeableImageView>(R.id.ivSlider)

        fun bind(imageUrl: ImageGallery){
                Glide.with(itemView.context)
                    .load(imageUrl.large)
                    .into(sliderImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderImageHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_item,parent,false)
        return SliderImageHolder(view)
    }

    override fun onBindViewHolder(holder: SliderImageHolder, position: Int) {
        holder.bind(mImageList [position])
    }

    override fun getItemCount(): Int = mImageList.size
}