package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topdark.databinding.ActivityCrearMisionBinding

class ActivityCrearMision : AppCompatActivity() {
    lateinit var binding: ActivityCrearMisionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearMisionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolverCrearMision.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

    }
}