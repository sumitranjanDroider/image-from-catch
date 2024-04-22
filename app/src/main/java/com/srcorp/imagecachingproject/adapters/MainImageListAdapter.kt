package com.srcorp.imagecachingproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.srcorp.imagecachingproject.R
import com.srcorp.imagecachingproject.dataModel.BitMapData
import com.srcorp.imagecachingproject.dataModel.Thumbnail
import com.srcorp.imagecachingproject.databinding.MainImageListAdapterItemBinding

class MainImageListAdapter:RecyclerView.Adapter<MainImageListAdapter.ImageViewHolder>() {
    var list= mutableListOf<BitMapData>()
    fun updateMainImageListAdapter(list: List<BitMapData>){
        val diffResult=DiffUtil.calculateDiff(MainAdapterDiffUtil(this.list,list))
        this.list=list.toMutableList()
        diffResult.dispatchUpdatesTo(this)

    }
    class ImageViewHolder(var binding:MainImageListAdapterItemBinding) :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding=DataBindingUtil.inflate<MainImageListAdapterItemBinding>(LayoutInflater.from(parent.context),
            R.layout.main_image_list_adapter_item,parent,false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val obj=list[holder.adapterPosition]
        holder.binding.apply {
            image=obj.imageUrl
            key=obj.key
        }
    }
}