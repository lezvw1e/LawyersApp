package com.example.lawyersapp.ui.fragment.lawyer.lawyarsAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lawyersapp.databinding.RvItemBinding
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity

class LawyersAdapter(
    private val listener: Results
): RecyclerView.Adapter<LawyersAdapter.ViewHolder>() {

    var list = listOf<LawyersEntity>()
        @SuppressLint("NotifyDataSetChanged")
        set(value)  {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
        holder.binding.imgMore.setOnClickListener {
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

    inner class ViewHolder(val binding: RvItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position : Int) = with(binding) {
            Glide.with(imgLawyer).load(list[position].image).into(imgLawyer)
            txtName.text = list[position].name
            txtWorkExperience.text = list[position].work_experience
        }
    }

    interface Results {
        fun onClickListener(
            id: String,
            name: String,
            work: String,
            image: String,
            special: String,
            phone_number: String)
    }
}