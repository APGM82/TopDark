package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.topdark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get data from Intent


        } else { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConectar.setOnClickListener{
        val intent = Intent(this, ActivityVader::class.java)
        resultLauncher.launch(intent)
        }

        binding.buttonprueba.setOnClickListener{
            val intent = Intent(this, ActivityHomePiloto::class.java)
            resultLauncher.launch(intent)
        }

    }
}