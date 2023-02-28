package com.example.lawyersapp.ui.fragment.on_board


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.FragmentOnBoardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


@Suppress("DEPRECATION")
class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var adapter: OnBoardAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setHasOptionsMenu(true)
        initAnimate()
        initClick()
        initDefault()
        initListener()
        TabLayoutMediator(
            binding.boardTab, binding.onboardPager
        ) { tab: TabLayout.Tab, _: Int ->
            tab.setIcon(
                R.drawable.tab_selector
            )
        }.attach()
    }

    private fun initAdapter() {
        adapter = OnBoardAdapter()
        binding.onboardPager.adapter = adapter
    }


    private fun initDefault() {
        binding.btnBoardStart.translationX = (-100).toFloat()
        binding.btnBoard.isEnabled = false
    }

    private fun initAnimate() {
        binding.btnBoardStart.animate().translationX(0F).setDuration(2000).start()
    }

    private fun initClick() {
        binding.btnBoard.setOnClickListener { findNavController().navigateUp() }
        binding.btnBoardStart.setOnClickListener { binding.onboardPager.currentItem = 2 }
    }

    private fun initListener() {
        binding.onboardPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.btnBoard.animate().alpha(1F).setDuration(2000).start()
                    binding.btnBoard.isEnabled = true
                    binding.btnBoardStart.animate().alpha(0F).setDuration(1000).start()
                    binding.btnBoardStart.isEnabled = false
                } else {
                    binding.btnBoardStart.animate().alpha(1F).setDuration(2000).start()
                    binding.btnBoardStart.isEnabled = true
                    binding.btnBoard.animate().alpha(0F).setDuration(1000).start()
                    binding.btnBoard.isEnabled = false
                }
            }
        })
    }
}