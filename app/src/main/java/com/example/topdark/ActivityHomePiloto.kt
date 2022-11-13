package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topdark.databinding.ActivityAltaNaveBinding
import com.example.topdark.databinding.ActivityHomePilotoBinding

class ActivityHomePiloto : AppCompatActivity() {
    lateinit var binding: ActivityHomePilotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePilotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolverHomePiloto.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
    }
}