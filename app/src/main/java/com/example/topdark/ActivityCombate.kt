package com.example.topdark

import Auxiliar.Conexion
import Modelo.MisionBombardeo
import Modelo.MisionCombate
import Modelo.MisionVuelo
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.topdark.databinding.ActivityBombardeoBinding
import com.example.topdark.databinding.ActivityCombateBinding

class ActivityCombate : AppCompatActivity() {
    lateinit var binding: ActivityCombateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCombateBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnVolverMisionCombate.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnMisionOkCombate.setOnClickListener {
            var sumamisiones=0
            var v= Conexion.obtenerMisionesVuelo(this)
            for (i in v){sumamisiones++}
            var c= Conexion.obtenerMisionesCombate(this)
            for (i in c){sumamisiones++}
            var b= Conexion.obtenerMisionesBombardeo(this)
            for(i in b){sumamisiones++}
            Toast.makeText(this,sumamisiones.toString(),Toast.LENGTH_SHORT).show()
            if (!binding.txtCazas.text.trim().toString().isNullOrEmpty()){
                if (binding.txtCazas.text.trim().toString().isDigitsOnly()){
                    var mision: MisionCombate = MisionCombate(sumamisiones,binding.txtCazas.text.toString().toInt(),"","",false)
                    Conexion.addMisionCombate(this, mision)
                }
            }
        }
    }
}