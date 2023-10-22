package com.example.clickermain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.clickermain.databinding.FragmentStartBinding
import com.example.clickermain.model.ClickViewModel


class StartFragment : Fragment() {
    private var binding: FragmentStartBinding? = null
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.startFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun navigate(id: Int) {
        if (id == 1) {
            findNavController().navigate(R.id.action_startFragment_to_homeFragment)
        } else{
            findNavController().navigate(R.id.action_startFragment_to_roomFragment)
        }

    }
}
