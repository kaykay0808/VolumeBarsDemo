package com.kay.volumebarsdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kay.volumebarsdemo.databinding.FragmentVolumeBinding

class VolumeFragment : Fragment() {

    private var _binding: FragmentVolumeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVolumeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Set Lines Button
        binding.setLinesBtn.setOnClickListener {
            // Set Line TextInput
            val lineInputString = binding.setLinesTextInput.editText?.text?.toString()
            val numberOfLines = lineInputString?.toIntOrNull()
            numberOfLines?.let { binding.volumeView.updateNumberOfLines(it) }
        }
        // Set volume button
        binding.setVolumeBtn.setOnClickListener {
            // Set volume textInput
            val volumeInputString = binding.setVolumeTextInput.editText?.text?.toString()
            val volumeLevel = volumeInputString?.toIntOrNull()
            volumeLevel?.let { binding.volumeView.updateVolumeLevel(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
