package com.example.search.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.search.domain.model.Item

object ItemDiffCallBack {

    val ITEM_DIFF_UTIL = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}