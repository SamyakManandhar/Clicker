package com.example.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.clickermain.databinding.FragmentSettingsBinding
import com.example.clickermain.model.ClickViewModel


class SettingsFragment : Fragment() {
    private var binding: FragmentSettingsBinding? = null
    private val sharedViewModel: ClickViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
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
            settingsFragment = this@SettingsFragment
        }
    }

    fun navigate() {
        val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
        view?.findNavController()?.navigate(action)
    }

    fun onSubmitWord() {
//        val ins = binding?.textInputEditText?.text.toString()
//        val outs = binding?.textInputEditText1?.text.toString()
//        val capa = binding?.textInputEditText2?.text.toString()
//        if (ins.toInt() > outs.toInt()) {
//            setErrorTextField(false)
//            sharedViewModel.setIns(ins.toInt())
//            sharedViewModel.setOuts(outs.toInt())
//        }
//        if (capa.toInt() > (sharedViewModel.capacity.value!!)) {
//            setErrorTextField(false)
//            sharedViewModel.setCapacity(capa.toInt())
//        } else {
//            setErrorTextField(true)
//        }
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding?.textField?.isErrorEnabled = true
            binding?.textField?.error = getString(R.string.try_again)
        } else {
            binding?.textField?.isErrorEnabled = false
            binding?.textInputEditText?.text = null
            val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
}