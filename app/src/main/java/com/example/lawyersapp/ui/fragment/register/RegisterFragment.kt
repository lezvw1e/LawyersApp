package com.example.lawyersapp.ui.fragment.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.R
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.databinding.FragmentRegisterBinding
import com.example.lawyersapp.ui.activity.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var edPhone1: String
    private lateinit var edPhone2: String
    private lateinit var launcher: ActivityResultLauncher<Intent>


    override fun setupUI() {
        mAuth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    account.idToken?.let { it1 -> firebaseAuthWithGoogle(it1) }
                }
            } catch (e: ApiException) {
            }
        }
        initFirebase()
        getClient()

        requireBinding().imgGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(requireActivity(), gso)
    }


    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigateUp()
                findNavController().navigate(R.id.mainFragment)
            }
        }
    }

    private fun initFirebase() {
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
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

            override fun onVerificationFailed(p0: FirebaseException) {
                makeText(requireContext(), p0.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                val bundle = Bundle()
                bundle.putString("phone", edPhone2)
                bundle.putString("id", id)
                findNavController().navigateUp()
                findNavController().navigate(R.id.interFragment, bundle)
            }
        }
    }


    override fun setupObservers() {
        super.setupObservers()
        initBtn()
    }

    private fun initBtn() {
        requireBinding().btnRegister.setOnClickListener {
            edPhone1 = requireBinding().edPhone1.text.toString().trim()
            edPhone2 = requireBinding().edPhone2.text.toString().trim()

            if (edPhone2.isNotEmpty() && edPhone1.isNotEmpty()) {
                if (edPhone2 == edPhone1) {

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        edPhone2, 60, TimeUnit.SECONDS,
                        activity as MainActivity, mCallback
                    )

                } else {
                    makeText(requireContext(), "${R.string.repeat_nomer_not_repeat}", Toast.LENGTH_SHORT).show()
                }
            } else {
                makeText(requireContext(), "${R.string.pole_not_empty}", Toast.LENGTH_SHORT).show()
            }

        }



    }
}