package com.example.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.clickermain.databinding.FragmentRoomBinding
import com.example.clickermain.model.ClickViewModel


class RoomFragment : Fragment() {
    private var binding: FragmentRoomBinding? = null
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentRoomBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            roomFragment = this@RoomFragment
        }
    }

    fun onSubmitWord() {
        val play = binding?.textInputEditText?.text.toString()
        sharedViewModel.setCapacity(play.toInt())
        val action = RoomFragmentDirections.actionRoomFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    fun navigate() {
        val action = RoomFragmentDirections.actionRoomFragmentToStartFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}