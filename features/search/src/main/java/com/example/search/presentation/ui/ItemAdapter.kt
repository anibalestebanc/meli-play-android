package com.example.search.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.search.databinding.SearchItemBinding
import com.example.search.domain.model.Item
import com.example.search.presentation.ui.ItemDiffCallBack.ITEM_DIFF_UTIL

class ItemAdapter(
    private var onClicked: (itemID: String) -> Unit
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ITEM_DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ItemViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.itemId.text = item.id
            binding.itemId.setOnClickListener {
                onClicked(item.id)
            }
        }
    }
}