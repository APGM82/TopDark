package com.example.topdark

import Auxiliar.Conexion
import Auxiliar.Conexion.addMisionVuelo
import Auxiliar.Conexion.obtenerMisionesBombardeo
import Auxiliar.Conexion.obtenerMisionesCombate
import Auxiliar.Conexion.obtenerMisionesVuelo
import Auxiliar.Conexion.obtenerNaves
import Modelo.MisionVuelo
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.topdark.databinding.ActivityVueloBinding

class ActivityVuelo : AppCompatActivity() {
    lateinit var binding: ActivityVueloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVueloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolverMisionVuelo.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnMisionOkVuelo.setOnClickListener {
            var sumamisiones=0
            var v= obtenerMisionesVuelo(this)
            for (i in v){sumamisiones++}
            var c= obtenerMisionesCombate(this)
            for (i in c){sumamisiones++}
            var b= obtenerMisionesBombardeo(this)
            for(i in b){sumamisiones++}
            Toast.makeText(this,sumamisiones.toString(),Toast.LENGTH_SHORT).show()
            if (!binding.txtDuracionVuelo.text.trim().toString().isNullOrEmpty()){
                if (binding.txtDuracionVuelo.text.trim().toString().isDigitsOnly()){
                   if(binding.txtDuracionVuelo.text.trim().toString().toInt()<30){
                       Toast.makeText(this,"Debe tener un valor de 30 como mínimo",Toast.LENGTH_SHORT).show()
                   }else{ var mision:MisionVuelo=MisionVuelo(sumamisiones,binding.txtDuracionVuelo.text.toString().toInt(),"","",0)
                    addMisionVuelo(this,mision)
                    //Toast.makeText(this,"Se ha creado la misión",Toast.LENGTH_SHORT).show()
                   }
                }
            }
        }
    }
}