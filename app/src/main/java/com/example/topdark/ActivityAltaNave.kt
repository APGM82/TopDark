package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topdark.databinding.ActivityAltaNaveBinding

class ActivityAltaNave : AppCompatActivity() {
    lateinit var binding: ActivityAltaNaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaNaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolverAltaNave.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnOkAltaNave.setOnClickListener{

        }

    }
}