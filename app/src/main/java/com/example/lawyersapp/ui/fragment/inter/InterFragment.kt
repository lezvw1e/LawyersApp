package com.example.lawyersapp.ui.fragment.inter

import android.os.CountDownTimer
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.R
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.databinding.FragmentInterBinding
import com.example.lawyersapp.ui.activity.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class InterFragment : BaseFragment<FragmentInterBinding>(FragmentInterBinding::inflate) {


    private lateinit var mAuth: FirebaseAuth
    private lateinit var edPhone : String
    private lateinit var mId : String
    private var timer : CountDownTimer? = null
    private var timerRunning: Boolean = false
    private var timeMillis: Long = 120000
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun setupUI() {
        if (arguments != null){
            edPhone = arguments?.getString("phone")!!
            mId = arguments?.getString("id")!!
        }
        initFirebase()
    }

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    } else {
                        makeText(requireContext(), task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                makeText(requireContext(), p0.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                mId = id
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        initBtn()
        startCountDownTimer()
    }

    private fun startCountDownTimer() {
        object : CountDownTimer(timeMillis, 1000) {
            override fun onTick(p0: Long) {
                timeMillis = p0
                upDateTimer()
            }

            override fun onFinish() {
                timerRunning = true
                requireBinding().btnRegister.text = "${R.string.inter_this}"
            }

        }.start()
    }

    private fun upDateTimer() {
        val minutes : Int = (timeMillis / 60000).toInt()
        val seconds : Int = (timeMillis % 60000 / 1000).toInt()
        var timeLeftText : String?
        timeLeftText = "" + minutes
        timeLeftText += ":"
        if (seconds < 10) timeLeftText += "0"
        timeLeftText += seconds
        try {
            requireBinding().txtTimer.text = timeLeftText
        } catch (_:Exception){}
    }

    private fun initBtn() {
        requireBinding().btnRegister.setOnClickListener {
            if (timerRunning) {
                repeatCode()
                timerRunning = false
                timeMillis = 120000
                startCountDownTimer()
            } else {
                if (requireBinding().edEmailId.text.toString().trim().isEmpty()) {
                    makeText(
                        requireContext(),
                        "${R.string.pass_and_email_not_empty}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    enterCode()
                }
            }
        }
    }

    private fun repeatCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            edPhone, 60, TimeUnit.SECONDS,
            activity as MainActivity, mCallback
        )
    }

    private fun enterCode() {
        val code = requireBinding().edEmailId.text.toString().trim()
        val credential = PhoneAuthProvider.getCredential(mId , code)

        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                makeText(requireContext(), "${R.string.wellcome}", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
                findNavController().navigate(R.id.mainFragment)
            } else {
                makeText(requireContext(), task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}