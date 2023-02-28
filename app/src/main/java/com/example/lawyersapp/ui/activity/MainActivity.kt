package com.example.lawyersapp.ui.activity

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.lawyersapp.App
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
         ActivityMainBinding.inflate(layoutInflater)
    }
    private val controller :NavController by lazy {
         val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navHostFragment.navController
    }
    private val list = arrayOf(
            R.id.mainFragment,
            R.id.settingFragment,
            R.id.documentFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        initLan(App.prefs.getString("score", "")!!)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavController()
        iniBottomNav()
    }

    private fun iniBottomNav() {
        binding.bottomNav.apply {
            setupWithNavController(controller)
            itemIconTintList = null
        }
    }

    private fun initNavController(){
      controller.addOnDestinationChangedListener{_,destination,_ ->
          if (list.contains(destination.id)) {
              binding.bottomNav.visibility = View.VISIBLE
          } else {
              binding.bottomNav.visibility = View.GONE
          }
        }
    }

    private fun initLan(score: String) {
        val resources: Resources = resources
        val configuration : Configuration = resources.configuration
        val locale = Locale(score)
        Locale.setDefault(locale)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}