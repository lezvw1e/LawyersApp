package com.example.lawyersapp.ui.fragment.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyersapp.databinding.ItemServicesBinding
import com.example.lawyersapp.domain.services.entity.ServicesEntity

class ServicesAdapter: RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    var list = listOf<ServicesEntity>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(pos: Int) {
                binding.txtName.text = list[pos].name
                binding.txtDesc.text = list[pos].desc
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemServicesBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }
}