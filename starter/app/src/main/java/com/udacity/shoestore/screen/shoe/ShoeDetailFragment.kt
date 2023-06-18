package com.udacity.shoestore.screen.shoe

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private var binding: FragmentShoeDetailBinding? = null

    private val args by navArgs<ShoeDetailFragmentArgs>()

    private val viewModel by activityViewModels<ShoeViewModel>()

    private val detailViewModel by viewModels<ShoeDetailViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ShoeDetailViewModel(args.shoe) as T
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container,
            false,
        )
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { binding ->
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
            binding.detailViewModel = detailViewModel
            binding.nameTextInputLayout.hint = buildSpannedString {
                append(getString(R.string.shoe_name))
                append(" ")
                color(Color.RED) {
                    append("*")
                }
            }
            binding.saveButton.isEnabled = args.shoe != null
            binding.nameEditText.addTextChangedListener(afterTextChanged = {
                val hasError = it.isNullOrBlank()
                binding.saveButton.isEnabled = !hasError
                binding.nameTextInputLayout.error =
                    if (hasError) {
                        getString(R.string.name_is_required)
                    } else {
                        null
                    }
            })

            binding.cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.eventShoeSaved.observe(viewLifecycleOwner) { saved ->
            if (saved) {
                findNavController().navigateUp()
                viewModel.onEventShoeSavedCompleted()
            }
        }

        detailViewModel.eventCancel.observe(viewLifecycleOwner) { cancelled ->
            if (cancelled) {
                findNavController().popBackStack()
                detailViewModel.onEventCancelCompleted()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}