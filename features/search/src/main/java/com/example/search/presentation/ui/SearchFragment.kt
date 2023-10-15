package com.example.search.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.navigation.DeepLinkFactory
import com.example.navigation.DeepLinkHandler
import com.example.search.R
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.presentation.SearchUiState
import com.example.search.presentation.SearchViewModel
import com.example.search.presentation.di.SearchProvider
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val provider = SearchProvider.create()

    private val searchAdapter = ItemAdapter(::onClickedItem)

    private val searchViewModel: SearchViewModel by lazy {
        SearchViewModel(provider.useCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        setupObservers()
        setupRecyclerView()
        searchViewModel.searchByText("motorola")
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchState.collect(::renderUI)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = searchAdapter
        }
    }

    private fun renderUI(state: SearchUiState) {
        when {
            state.isLoading -> {
            }

            state.items != null -> {
                searchAdapter.submitList(state.items)
            }
        }
    }


    private fun onClickedItem(itemId: String) {
        val deepLink = DeepLinkFactory.create("detail", mapOf("item_id" to itemId))
        DeepLinkHandler.openDeepLink(requireContext(), deepLink)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}