package com.example.detail.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.detail.R
import com.example.detail.databinding.FragmentDetailBinding
import com.example.detail.domain.model.ItemDetail
import com.example.detail.presentation.DetailUiState
import com.example.detail.presentation.DetailViewModel
import com.example.detail.presentation.di.DetailProvider
import com.example.navigation.Constants.ITEM_ID_KEY
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val provider = DetailProvider.create()

    private val detailViewModel: DetailViewModel by lazy {
        DetailViewModel(provider.repository)
    }

    private val formatter = DecimalFormat("#,###")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        setupObservers()
        val itemId = requireActivity().intent.data?.getQueryParameter(ITEM_ID_KEY).orEmpty()
        detailViewModel.getItemById(itemId)
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.detailState.collect(::renderUI)
            }
        }
    }

    private fun renderUI(state: DetailUiState) {
        when {
            state.isLoading -> {
            }

            state.itemDetail != null -> setItemDetail(state.itemDetail)

        }
    }

    private fun setItemDetail(itemDetail: ItemDetail) {
        binding.imageItem.load(itemDetail.pictures[0].secureUrl) {
            crossfade(true)
            transformations(RoundedCornersTransformation())
            placeholder(R.drawable.image_placeholder)
        }
        binding.itemTitle.text = itemDetail.title
        binding.itemOriginalPrice.text = itemDetail.originalPrice.toString()

        val priceFormatted = formatter.format(itemDetail.price)
        binding.itemPrice.text = buildString {
            append("$ ")
            append(priceFormatted)
        }

        if (itemDetail.originalPrice > 0) {
            val beforePrice = binding.root.context.getString(R.string.before)
            val beforePriceFormatted = formatter.format(itemDetail.originalPrice)
            binding.itemOriginalPrice.text = buildString {
                append(beforePrice)
                append("  $ ")
                append(beforePriceFormatted)
            }
            binding.itemOriginalPrice.visibility = View.VISIBLE
        } else {
            binding.itemOriginalPrice.visibility = View.GONE
        }

        if (!itemDetail.officialStoreName.isNullOrEmpty()) {
            val sellerBy = binding.root.context.getString(R.string.seller_by)
            binding.sellerBy.text = buildString {
                append(sellerBy)
                append(" ")
                append(itemDetail.officialStoreName)
            }
            binding.sellerBy.visibility = View.VISIBLE
        } else {
            binding.sellerBy.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}