package com.example.detail.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.detail.R
import com.example.detail.databinding.FragmentDetailBinding
import com.example.detail.presentation.DetailUiState
import com.example.detail.presentation.DetailViewModel
import com.example.detail.presentation.di.DetailProvider
import com.example.navigation.Constants.ITEM_ID_KEY
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val provider = DetailProvider.create()

    private val detailViewModel: DetailViewModel by lazy {
        DetailViewModel(provider.repository)
    }

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

            state.itemDetail != null -> {
                binding.itemId.text = state.itemDetail.id
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}