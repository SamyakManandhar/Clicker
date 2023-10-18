package com.example.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.clickermain.databinding.FragmentRoomBinding
import com.example.clickermain.model.ClickViewModel


class RoomFragment : Fragment() {
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.submit.setOnClickListener {
            onSubmitWord()
            val action = RoomFragmentDirections.actionRoomFragmentToHomeFragment()
            view.findNavController().navigate(action)
        }
        binding.cancel.setOnClickListener {
            val action = RoomFragmentDirections.actionRoomFragmentToStartFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun onSubmitWord() {
        val play = binding.textInputEditText.text.toString()
        sharedViewModel.setCapacity(play.toInt())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}