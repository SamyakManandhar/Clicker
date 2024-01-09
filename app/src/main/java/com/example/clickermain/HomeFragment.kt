package com.example.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.clickermain.databinding.FragmentHomeBinding
import com.example.clickermain.model.ClickViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkmode()
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            homeFragment = this@HomeFragment
        }

    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.capacity))
            .setMessage(getString(R.string.message, sharedViewModel.capacity.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                Toast.makeText(requireContext(), R.string.cancelled, Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton(getString(R.string.increase)) { _, _ ->
                val action = HomeFragmentDirections.actionHomeFragmentToRoomFragment()
                view?.findNavController()?.navigate(action)
            }
            .show()
    }

    fun checkouts() {
        if (sharedViewModel.ins.value!! > sharedViewModel.outs.value!!) {
            sharedViewModel.setOuts()
        } else {
            Toast.makeText(requireContext(), R.string.empty, Toast.LENGTH_SHORT).show()
        }
    }

    fun checkstat() {
        if (sharedViewModel.checkCap() && sharedViewModel.checkLimit()) {
            showDialog()
        } else {
            sharedViewModel.setIns()
        }
    }


    private fun checkmode() {
        if (sharedViewModel.checkCap()) {
            binding?.apply {
                cap.visibility = View.VISIBLE
                divider2.visibility = View.VISIBLE
                capacity.visibility = View.VISIBLE
            }
        } else {
            binding?.cap?.visibility = View.INVISIBLE
            binding?.divider2?.visibility = View.INVISIBLE
            binding?.capacity?.visibility = View.INVISIBLE

        }
    }


    fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.confirmation))
            .setCancelable(true)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                Toast.makeText(requireContext(), R.string.cancelled, Toast.LENGTH_SHORT).show()
            }.setPositiveButton(getString(R.string.clear)) { _, _ ->
                sharedViewModel.reset()
                val action = HomeFragmentDirections.actionHomeFragmentSelf()
                view?.findNavController()?.navigate(action)
            }
            .show()
    }

    fun navigate() {
        val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
        view?.findNavController()?.navigate(action)
    }

    private fun exit() {
        activity?.finish()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}