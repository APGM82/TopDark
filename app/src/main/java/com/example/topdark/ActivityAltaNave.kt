package com.example.topdark

import Auxiliar.Conexion.addNave
import Modelo.Naves
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import com.example.topdark.databinding.ActivityAltaNaveBinding


class ActivityAltaNave : AppCompatActivity() {
    lateinit var binding: ActivityAltaNaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaNaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tipo:String=""


        binding.rbCaza.setOnClickListener {
            binding.chkPasajeros.isChecked=false
            binding.chkCarga.isChecked=false
            binding.chkPasajeros.isEnabled=false
            binding.chkCarga.isEnabled=false

            binding.chkLayout.isVisible=false
            binding.imgNave.setImageResource(R.drawable.lambdashuttle)
            tipo="caza"
        }
        binding.rbBombardero.setOnClickListener {

            binding.chkLayout.isVisible=true
            binding.chkPasajeros.isEnabled=true
            binding.chkCarga.isEnabled=true
            binding.imgNave.setImageResource(R.drawable.tiebomber)
            tipo="bombardero"
        }
        binding.rbCarga.setOnClickListener {

            binding.chkLayout.isVisible=true
            binding.chkPasajeros.isEnabled=true
            binding.chkCarga.isEnabled=true
            binding.imgNave.setImageResource(R.drawable.tiefighter)
            tipo="carguero"
        }





        binding.btnVolverAltaNave.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnOkAltaNave.setOnClickListener{
            var pasa:Boolean=false
            var carg:Boolean=false

            if(binding.chkPasajeros.isChecked==false){
                pasa=false
            }else{pasa=true}

            if(binding.chkCarga.isChecked==false){
                carg=false
            }else{carg=true}
            if (!binding.txtMatricula.text.trim().toString().isNullOrEmpty() && (binding.rbCaza.isChecked || binding.rbBombardero.isChecked || binding.rbCarga.isChecked)){
                var n:Naves=Naves(binding.txtMatricula.text.trim().toString(),tipo,carg,pasa,tipo)
                addNave(this,n)
                Toast.makeText(this,getResources().getString(R.string.creada),Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,getResources().getString(R.string.errorMatricula),Toast.LENGTH_SHORT).show()
            }

        }

    }
}