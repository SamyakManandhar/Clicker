package com.example.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
            updateScreen()
        }

        binding.minusButton.setOnClickListener {
            if (sharedViewModel.ins > sharedViewModel.outs)
            {
                sharedViewModel.setOuts()
                sharedViewModel.getTotal()
                updateScreen()
            }
        }
    }

    private fun updateScreen() {
        binding.inCount.text = sharedViewModel.ins.toString()
        binding.outCount.text = sharedViewModel.outs.toString()
        binding.total.text = sharedViewModel.total.toString()
        binding.capacity.text = sharedViewModel.capacity.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}