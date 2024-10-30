package com.app.dailyjounral

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.app.dailyjounral.databinding.ActivityMainBinding
import com.app.dailyjounral.databinding.TestActivityBinding


class TestActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: TestActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}