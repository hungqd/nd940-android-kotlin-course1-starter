package com.udacity.shoestore.screen.shoe

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding

class ShoeListFragment : Fragment() {

    private var binding: FragmentShoeListBinding? = null

    private val viewModel by activityViewModels<ShoeViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_shoe_list, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return if (menuItem.itemId == R.id.logout) {
                        findNavController().navigate(R.id.action_logout)
                        true
                    } else {
                        false
                    }
                }
            },
            this,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false,
        )
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.shoes.observe(viewLifecycleOwner) { shoes ->
            binding?.let { binding ->
                binding.shoeListLinearLayout.removeAllViews()
                shoes.forEach { shoe ->
                    val shoeView = DataBindingUtil.inflate<ItemShoeBinding>(
                        layoutInflater,
                        R.layout.item_shoe,
                        binding.shoeListLinearLayout,
                        true
                    )
                    shoeView.shoe = shoe
                }
            }
        }
    }
}