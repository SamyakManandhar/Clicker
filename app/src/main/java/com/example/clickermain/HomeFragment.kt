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
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateScreen()
        binding.plusButton.setOnClickListener {
            sharedViewModel.setIns()
            sharedViewModel.getTotal()
            if (sharedViewModel.checkCap() && sharedViewModel.checkLimit()) {
                showDialog()
            } else {
                updateScreen()
            }
        }
        binding.clearButton.setOnClickListener {
            showAlertDialog()
        }

        binding.minusButton.setOnClickListener {
            if (sharedViewModel.ins > sharedViewModel.outs) {
                sharedViewModel.setOuts()
                sharedViewModel.getTotal()
                updateScreen()
            }
        }
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.capacity))
            .setMessage(getString(R.string.message, sharedViewModel.capacity))
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

    private fun updateScreen() {
        binding.ins.text = sharedViewModel.ins.toString()
        binding.out.text = sharedViewModel.outs.toString()
        binding.num.text = sharedViewModel.total.toString()
        if (sharedViewModel.checkCap()) {
            binding.capacity.text = sharedViewModel.capacity.toString()
        } else {
            binding.divider2.visibility = View.INVISIBLE
            binding.capacity.visibility = View.INVISIBLE
            binding.cap.visibility = View.INVISIBLE
        }

    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.confirmation))
            .setCancelable(true)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                Toast.makeText(requireContext(), R.string.cancelled, Toast.LENGTH_SHORT).show()
            }.setPositiveButton(getString(R.string.clear)) { _, _ ->
                sharedViewModel.reset()
                updateScreen()
            }
            .show()
    }

    private fun exit() {
        activity?.finish()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}