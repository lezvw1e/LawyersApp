package com.example.lawyersapp.ui.fragment.on_board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.OnboardItemBinding

class OnBoardAdapter : Adapter<OnBoardAdapter.ViewHolder>() {

    private val msg: Array<String> = arrayOf(
        "Добро пожалолвать", "Наши преимущества", "Кто у нас работает"
    )

    private val img: Array<Int> = arrayOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
    )

    private val desc: Array<String> = arrayOf(
        "Мы рады что вы выбрали именно наше приложение",
        "Мы можем решить вашу любую проблему связанную с законом или нарушением ваших прав",
        "У нас работают опытные с многолетним стажем юристы, с которыми вы можете работать"
    )
    inner class ViewHolder(private val binding: OnboardItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(msg: String, img: Int, desc: String) = with(binding) {
                tvOnTitle.text = msg
                itemImg.setImageResource(img)
                tvDes.text = desc
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OnboardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(msg[position], img[position], desc[position])
    }

    override fun getItemCount(): Int = img.size
}