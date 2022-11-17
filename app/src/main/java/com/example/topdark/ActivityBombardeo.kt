package com.example.topdark

import Auxiliar.Conexion
import Modelo.MisionBombardeo
import Modelo.MisionVuelo
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.topdark.databinding.ActivityBombardeoBinding
import com.example.topdark.databinding.ActivityVueloBinding

class ActivityBombardeo : AppCompatActivity() {
    lateinit var binding: ActivityBombardeoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBombardeoBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnVolverMisionBombardeo.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnMisionOkBombardeo.setOnClickListener {
            var sumamisiones=0
            var v= Conexion.obtenerMisionesVuelo(this)
            for (i in v){sumamisiones++}
            var c= Conexion.obtenerMisionesCombate(this)
            for (i in c){sumamisiones++}
            var b= Conexion.obtenerMisionesBombardeo(this)
            for(i in b){sumamisiones++}
            Toast.makeText(this,sumamisiones.toString(),Toast.LENGTH_SHORT).show()
            if (!binding.txtObjetivos.text.trim().toString().isNullOrEmpty()){
                if (binding.txtObjetivos.text.trim().toString().isDigitsOnly()){
                    var mision:MisionBombardeo=MisionBombardeo(sumamisiones,binding.txtObjetivos.text.toString().toInt(),"","",0)
                    Conexion.addMisionBombardeo(this, mision)
                    //Toast.makeText(this,"Se ha creado la misión", Toast.LENGTH_SHORT).show()
                }
            }





        }
    }
}