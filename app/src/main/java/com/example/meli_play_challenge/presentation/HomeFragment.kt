package com.example.meli_play_challenge.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meli_play_challenge.R
import com.example.meli_play_challenge.databinding.FragmentHomeBinding
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
            navigateToSearch(text)
        }
    }

    private fun navigateToSearch(text: String) {
        val deepLink = DeepLinkFactory.create("search", mapOf("value" to text))
        DeepLinkHandler.openDeepLink(requireContext(), deepLink)
    }
}