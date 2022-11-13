package com.example.topdark

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.topdark.databinding.ActivityAltaNaveBinding


class ActivityAltaNave : AppCompatActivity() {
    lateinit var binding: ActivityAltaNaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaNaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //(TO DO) listener de los radio button para desmarcar y deshabilitar pasajeros y carga

        binding.rbCaza.setOnClickListener {
            binding.chkPasajeros.isEnabled=false
            binding.chkCarga.isEnabled=false

            binding.chkLayout.isVisible=false
            binding.imgNave.setImageResource(R.drawable.lambdashuttle)
        }
        binding.rbBombardero.setOnClickListener {

            binding.chkLayout.isVisible=true
            binding.chkPasajeros.isEnabled=true
            binding.chkCarga.isEnabled=true
            binding.imgNave.setImageResource(R.drawable.tiebomber)
        }
        binding.rbCarga.setOnClickListener {

            binding.chkLayout.isVisible=true
            binding.chkPasajeros.isEnabled=true
            binding.chkCarga.isEnabled=true
            binding.imgNave.setImageResource(R.drawable.tiefighter)
        }



        //(TO DO) meter la imagen personalizada al hacer clic con el boton



        binding.btnVolverAltaNave.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnOkAltaNave.setOnClickListener{
            //(TO DO) guardar el resultado del ok en la base de datos
        }

    }
}