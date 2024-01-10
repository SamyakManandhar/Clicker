package com.application.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.application.clickermain.databinding.FragmentRoomBinding
import com.application.clickermain.model.ClickViewModel


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
        hintON()
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
        if (play.isNotBlank()) {
            if (play.toInt() > (sharedViewModel.capacity.value!!)) {
                setErrorTextField(false)
                sharedViewModel.setCapacity(play.toInt())
                val action = RoomFragmentDirections.actionRoomFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                setErrorTextField(true)
            }
        } else {
            setErrorTextField(true)
        }
    }

    fun navigate() {
        val action = RoomFragmentDirections.actionRoomFragmentToStartFragment()
        findNavController().navigate(action)
    }

    fun hintON() {
        if (sharedViewModel.checkCap()) {
            binding?.apply {
                textField.hint = getString(R.string.message, sharedViewModel.capacity.value)
            }
        }
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding?.textField?.isErrorEnabled = true
            binding?.textField?.error = getString(R.string.capahigh)
        } else {
            binding?.textField?.isErrorEnabled = false
            binding?.textInputEditText?.text = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}