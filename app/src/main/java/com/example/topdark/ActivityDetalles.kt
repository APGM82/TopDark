package com.example.topdark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topdark.databinding.ActivityDetallesBinding

class ActivityDetalles : AppCompatActivity() {
    lateinit var binding: ActivityDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var hola=intent.getStringExtra("id")
        binding.textView2.text=hola.toString()

    }
}