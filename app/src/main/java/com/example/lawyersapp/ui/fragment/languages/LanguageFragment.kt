package com.example.lawyersapp.ui.fragment.languages

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.App
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.FragmentLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LanguageFragment : BottomSheetDialogFragment() {

    private val binding : FragmentLanguageBinding by lazy {
        FragmentLanguageBinding.inflate(layoutInflater)
    }
    private lateinit var score : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rgLan.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.btnUzbek.id -> score = "uz"
                binding.txtEnglish.id -> score = "en"
                binding.txtKyrgyz.id -> score = "ky"
                binding.txtRussia.id -> score = ""
            }
        }

        binding.btnUse.setOnClickListener {
            App.prefs.edit().putString("score", score).apply()
            initLan(score)
            findNavController().navigateUp()
            requireActivity().finish()
            requireActivity().startActivity(requireActivity().intent)
        }
    }

    private fun initLan(score: String) {
        val resources: Resources = resources
        val configuration : Configuration = resources.configuration
        val locale = Locale(score)
        Locale.setDefault(locale)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        binding.btnUse.text = getString(R.string.use)
    }
}