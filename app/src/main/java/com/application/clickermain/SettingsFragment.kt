package com.application.clickermain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.application.clickermain.databinding.FragmentSettingsBinding
import com.application.clickermain.model.ClickViewModel


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
        insFocusListener()
        outsFocusListener()
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            settingsFragment = this@SettingsFragment
        }
    }


    fun navigate(num: Int) {
        when (num) {
            1 -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToRoomFragment()
                findNavController().navigate(action)
            }

            2 -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
                view?.findNavController()?.navigate(action)
            }
        }

    }

    fun onSubmitWord() {
        val ins = binding?.textInputEditText?.text.toString()
        val outs = binding?.textInputEditText1?.text.toString()
        if (ins.isNotBlank() && outs.isNotBlank()) {
            if (ins.toInt() > outs.toInt()) {
                sharedViewModel.setIns(ins.toInt())
                sharedViewModel.setOuts(outs.toInt())
                val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
                view?.findNavController()?.navigate(action)
            } else {
                Toast.makeText(requireContext(), R.string.warningOuts, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), R.string.required, Toast.LENGTH_SHORT).show()
        }
    }


    private fun insFocusListener() {
        binding?.textInputEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                validIns()
            }
        }
    }

    private fun validIns() {
        val ins = binding?.textInputEditText?.text.toString()
        val outs = binding?.textInputEditText1?.text.toString()
        if (outs.isNotEmpty()&&ins.isNotEmpty()) {
            if (outs.toInt() > ins.toInt()) {
                setErrorTextField(true)
            } else {
                setErrorTextField(false)
            }
        } else {
            setErrorTextField(false)
        }
    }


    private fun outsFocusListener() {

        binding?.textInputEditText1?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                validOuts()
            }
        }
    }

    private fun validOuts() {
        val ins = binding?.textInputEditText?.text.toString()
        val outs = binding?.textInputEditText1?.text.toString()
        if (ins.isNotEmpty()&&outs.isNotEmpty()) {
            if (outs.toInt() > ins.toInt()) {
                setErrorTextField1(true)
            } else {
                setErrorTextField1(false)
            }
        } else {
            setErrorTextField1(false)
        }
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding?.textField?.isErrorEnabled = true
            binding?.textField?.error = getString(R.string.warningIns)
        } else {
            binding?.textField?.isErrorEnabled = false
            binding?.textField?.helperText = null
        }
    }

    private fun setErrorTextField1(error: Boolean) {
        if (error) {
            binding?.textField1?.isErrorEnabled = true
            binding?.textField1?.error = getString(R.string.warningOuts)
        } else {
            binding?.textField1?.isErrorEnabled = false
            binding?.textField1?.helperText = null
        }
    }
}

