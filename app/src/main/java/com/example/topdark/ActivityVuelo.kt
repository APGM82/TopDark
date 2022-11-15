package com.example.topdark

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
import android.widget.Toast
import com.example.topdark.databinding.ActivityVueloBinding

class ActivityVuelo : AppCompatActivity() {
    lateinit var binding: ActivityVueloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVueloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sumamisiones:Int=0

        var v= obtenerMisionesVuelo(this)
        var c= obtenerMisionesCombate(this)
        var b= obtenerMisionesBombardeo(this)
        sumamisiones=v.size+c.size+b.size

        var mision:MisionVuelo=MisionVuelo(sumamisiones,binding.txtDuracionVuelo.text.toString().toInt(),"","",false)


        binding.btnVolverMisionVuelo.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnMisionOkVuelo.setOnClickListener {
            addMisionVuelo(this,mision)
            Toast.makeText(this,"Se ha creado la misión",Toast.LENGTH_SHORT).show()
        }
    }
}