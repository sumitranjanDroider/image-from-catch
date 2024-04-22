package com.srcorp.imagecachingproject.adapters

import androidx.recyclerview.widget.DiffUtil
import com.srcorp.imagecachingproject.dataModel.BitMapData
import com.srcorp.imagecachingproject.dataModel.Thumbnail

class MainAdapterDiffUtil(private val oldList: List<BitMapData>, private val newList: List<BitMapData>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].key==newList[newItemPosition].key
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]
    }
}