package com.example.lawyersapp.ui.fragment.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.lawyersapp.databinding.ItemLawyersBinding
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity

class LawyerAdapter(
    private val listener: Result
): RecyclerView.Adapter<LawyerAdapter.ViewHolder>(){

    var list = listOf<LawyersEntity>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemLawyersBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(pos: Int) {
                Glide.with(binding.imgLawyer).load(list[pos].image).into(binding.imgLawyer)
                binding.txtName.text = list[pos].name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLawyersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
        holder.itemView.setOnClickListener {
            listener.onClickListener(
                list[position].id,
                list[position].name,
                list[position].work_experience,
                list[position].image,
                list[position].special,
                list[position].phone_number)
        }
    }

    override fun getItemCount(): Int = list.size

    interface Result {
        fun onClickListener(
            id: String,
            name: String,
            work: String,
            image: String,
            special: String,
            phone_number: String)
    }
}