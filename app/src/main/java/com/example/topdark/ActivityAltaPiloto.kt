package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topdark.databinding.ActivityAltaPilotoBinding

class ActivityAltaPiloto : AppCompatActivity() {
    lateinit var binding: ActivityAltaPilotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaPilotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolverAltaPiloto.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnOkAltaPiloto.setOnClickListener{

        }
    }
}