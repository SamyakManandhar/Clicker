package com.example.clickermain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.clickermain.databinding.FragmentStartBinding
import com.example.clickermain.model.ClickViewModel


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.test.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToHomeFragment()
            view.findNavController().navigate(action)
        }
        binding.test1.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToRoomFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
