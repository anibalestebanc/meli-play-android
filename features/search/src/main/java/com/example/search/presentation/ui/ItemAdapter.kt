package com.example.search.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.search.R
import com.example.search.databinding.SearchItemBinding
import com.example.search.domain.model.Item
import com.example.search.presentation.ui.ItemDiffCallBack.ITEM_DIFF_UTIL
import java.text.DecimalFormat

class ItemAdapter(
    private var onClicked: (itemID: String) -> Unit
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ITEM_DIFF_UTIL) {

    private val formatter = DecimalFormat("#,###")
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
            binding.imageItem.load(item.thumbnail) {
                crossfade(true)
                transformations(RoundedCornersTransformation())
            }
            binding.itemTitle.text = item.title

            val priceFormatted = formatter.format(item.price)
            binding.itemPrice.text = buildString {
                append("$ ")
                append(priceFormatted)
            }

            if (item.originalPrice > 0) {
                val beforePrice = binding.root.context.getString(R.string.before)
                val beforePriceFormatted = formatter.format(item.originalPrice)
                binding.itemOriginalPrice.text = buildString {
                    append(beforePrice)
                    append("  $ ")
                    append(beforePriceFormatted)
                }
                binding.itemOriginalPrice.visibility = View.VISIBLE
            } else {
                binding.itemOriginalPrice.visibility = View.GONE
            }


            if (!item.officialStoreName.isNullOrEmpty()) {
                val sellerBy = binding.root.context.getString(R.string.seller_by)
                binding.sellerBy.text = buildString {
                    append(sellerBy)
                    append(" ")
                    append(item.officialStoreName)
                }
                binding.sellerBy.visibility = View.VISIBLE
            } else {
                binding.sellerBy.visibility = View.GONE
            }
            binding.root.setOnClickListener {
                onClicked(item.id)
            }
        }
    }
}