package com.udacity.shoestore.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToWelcomeEvent.observe(viewLifecycleOwner) { navToWelcomeScreen ->
            if (navToWelcomeScreen) {
                navigateToWelcomeScreen()
                viewModel.onNavigatedToWelcomeScreen()
            }
        }
        binding.loginButton.setOnClickListener {
            viewModel.login()
        }
        binding.newAccountButton.setOnClickListener {
            viewModel.createNewAccount()
        }
    }

    private fun navigateToWelcomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
    }
}