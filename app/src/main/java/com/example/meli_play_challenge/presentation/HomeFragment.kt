package com.example.meli_play_challenge.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meli_play_challenge.R
import com.example.meli_play_challenge.databinding.FragmentHomeBinding
import com.example.navigation.Constants.SEARCH_MODULE
import com.example.navigation.Constants.SEARCH_VALUE_KEY
import com.example.navigation.DeepLinkFactory
import com.example.navigation.DeepLinkHandler

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        setUpObservers()
    }

    private fun setUpObservers() {
        binding.searchButton.setOnClickListener {
            val text = binding.textField.editText?.text.toString()
            if (text.isNotEmpty()) {
                goToSearch(text)
            }
        }
    }

    private fun goToSearch(text: String) {
        val deepLink = DeepLinkFactory.create(SEARCH_MODULE, mapOf(SEARCH_VALUE_KEY to text))
        DeepLinkHandler.openDeepLink(requireContext(), deepLink)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}