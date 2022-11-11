package com.example.topdark

import Auxiliar.Conexion
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        var pil : Pilotos =Pilotos(binding.txtNombreAlta.text.toString(),(binding.txtEdadAlta.text.toString()).toInt(),0,"password",null)
        Conexion.addPiloto(this,pil)
            binding.txtNombreAlta.setText("")
            binding.txtEdadAlta.setText("")
            Toast.makeText(this,"Piloto añadido",Toast.LENGTH_SHORT).show()
        }
    }
}