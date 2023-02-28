package com.example.lawyersapp.ui.fragment.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.App
import com.example.lawyersapp.R
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.databinding.FragmentSettingBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate),
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    override fun setupUI() {

        requireBinding().txtLanguage.setOnClickListener {
            findNavController().navigate(R.id.languageFragment)
        }

        requireBinding().txtHelp.setOnClickListener {
            val number = "https://wa.me/+996702030607?text=Hello!"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(number)
            startActivity(intent)
        }
        if (!App.mGoogleApiInit){
            App.mGoogleApiClient = GoogleApiClient.Builder(requireContext())
                .enableAutoManage(
                    requireActivity() /* FragmentActivity */,
                    this /* OnConnectionFailedListener */
                )
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build()
        }
        requireBinding().txtOutAcc.setOnClickListener {

            Firebase.auth.signOut()

            Auth.GoogleSignInApi.signOut(App.mGoogleApiClient)

            requireActivity().finish()
            requireActivity().startActivity(requireActivity().intent)
        }
    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
}