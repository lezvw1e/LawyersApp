package com.example.lawyersapp.ui.fragment.document

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.ItemVpDocumentBinding

class DocumentAdapter : RecyclerView.Adapter<DocumentAdapter.ViewHolder>() {

    private val img : Array<Int> = arrayOf(
        R.drawable.lawyer,
    R.drawable.lawyer_2, R.drawable.img_3, R.drawable.img_4)

    inner class ViewHolder(private val binding: ItemVpDocumentBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(i: Int) {
            binding.imgDocument.setImageResource(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVpDocumentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(img[position])
    }
    override fun getItemCount(): Int = img.size
}