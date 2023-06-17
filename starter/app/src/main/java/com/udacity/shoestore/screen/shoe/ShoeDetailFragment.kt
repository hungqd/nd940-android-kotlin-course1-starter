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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import java.util.UUID

class ShoeDetailFragment : Fragment() {

    private var binding: FragmentShoeDetailBinding? = null

    private val viewModel by activityViewModels<ShoeViewModel>()

    private val args by navArgs<ShoeDetailFragmentArgs>()

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
            binding.shoe = args.shoe
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
            binding.saveButton.setOnClickListener {
                val name = binding.nameEditText.text.toString()
                val company = binding.companyEditText.text.toString()
                val size = binding.sizeEditText.text.toString().toDoubleOrNull() ?: 0.0
                val description = binding.descriptionEditText.text.toString()
                val shoe = args.shoe?.copy(
                    name = name,
                    company = company,
                    size = size,
                    description = description,
                ) ?: Shoe(
                    id = UUID.randomUUID(),
                    name = name,
                    company = company,
                    size = size,
                    description = description,
                )
                viewModel.saveShoe(shoe)
                findNavController().popBackStack()
            }

            binding.cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}