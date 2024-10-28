package com.app.dailyjounral

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.adapter.MoodAdapter
import com.app.dailyjounral.databinding.ActivityMainBinding
import com.app.dailyjounral.model.MoodDataModel
import com.app.secureglobal.interfaces.OnItemSelected


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        Log.e("Token",getSharedPreferences("_", MODE_PRIVATE).getString("fb","").toString())

    }
}