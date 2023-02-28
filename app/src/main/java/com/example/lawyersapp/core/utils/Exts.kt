package com.example.lawyersapp.core.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.PartResultBinding
import com.google.android.material.button.MaterialButton

fun View.visible() {
    this.isVisible = true
}

fun View.gone() {
    this.isVisible = false
}

fun Context.showToast(msg: String?) {
    msg?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
}

fun <B : ViewBinding, T : Any,
        N : Any> BaseFragment<B>.renderSimpleResult(
    root: ViewGroup,
    result: BaseResult<T, N>, onSuccess: (T) -> Unit,
    onError: (N?) -> Unit
) {
    val binding = PartResultBinding.bind(root)
    renderResult(
        root = root,
        result = result,
        onPending = {
            binding.progressBar.visible()
        },
        onError = { error ->
            binding.errorCont.visible()
            onError(error)
        },
        onSuccess = { data ->
            root.children
                .filter { it.id != R.id.progress_bar && it.id != R.id.error_cont }
                .forEach { it.visible() }
            onSuccess(data)
        }
    )
}

fun <B : ViewBinding> BaseFragment<B>.onTryAgainListener(
    root: View,
    onTryAgainAction: () -> Unit,
) {
    root.findViewById<MaterialButton>(R.id.btn_try_again).setOnClickListener {
        onTryAgainAction()
    }
}