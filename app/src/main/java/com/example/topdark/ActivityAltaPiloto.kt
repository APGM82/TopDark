package com.example.topdark

import Auxiliar.Conexion
import Auxiliar.Conexion.addPiloto
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.topdark.databinding.ActivityAltaPilotoBinding

class ActivityAltaPiloto : AppCompatActivity() {
    lateinit var binding: ActivityAltaPilotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaPilotoBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        binding.btnVolverAltaPiloto.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnOkAltaPiloto.setOnClickListener{
            try {
                if (!binding.txtNombreAlta.text.trim().toString().isNullOrEmpty() || (binding.txtEdadAlta.text.trim().toString().isDigitsOnly())){
                    var pil : Pilotos =Pilotos(binding.txtNombreAlta.text.trim().toString(),(binding.txtEdadAlta.text.trim().toString()).toInt(),0,"password",null)
                    addPiloto(this,pil)
                    binding.txtNombreAlta.setText("")
                    binding.txtEdadAlta.setText("")
                    Toast.makeText(this,getResources().getString(R.string.addPiloto),Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,getResources().getString(R.string.txtErrorRelleno),Toast.LENGTH_SHORT).show()
                }
            }catch ( e:Exception){
                Toast.makeText(this,getResources().getString(R.string.txtErrorIcorrecto),Toast.LENGTH_SHORT).show()
            }
        }

    }
}