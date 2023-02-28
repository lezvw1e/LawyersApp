package com.example.lawyersapp.ui.fragment.admin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.lawyersapp.databinding.ItemAdminBinding
import com.example.lawyersapp.domain.admin.entity.AdminEntity

class AdminAdapter : Adapter<AdminAdapter.ViewHolder>() {

    private val list = ArrayList<AdminEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun addAdmin(list: List<AdminEntity>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAdminBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(model: AdminEntity){
                binding.txtName.text = model.name
                binding.txtLastName.text = model.surname
                binding.txtPhone.text = model.phone
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAdminBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}