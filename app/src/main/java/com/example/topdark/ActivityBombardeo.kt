package com.example.topdark

import Auxiliar.Conexion
import Modelo.MisionBombardeo
import Modelo.MisionVuelo
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.topdark.databinding.ActivityBombardeoBinding
import com.example.topdark.databinding.ActivityVueloBinding

class ActivityBombardeo : AppCompatActivity() {
    lateinit var binding: ActivityBombardeoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBombardeoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sumamisiones:Int=0

        var v= Conexion.obtenerMisionesVuelo(this)
        var c= Conexion.obtenerMisionesCombate(this)
        var b= Conexion.obtenerMisionesBombardeo(this)
        sumamisiones=v.size+c.size+b.size

        var mision: MisionBombardeo =  MisionBombardeo(sumamisiones,binding.txtObjetivos.text.toString().toInt(),"","",false)


        binding.btnVolverMisionBombardeo.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnMisionOkBombardeo.setOnClickListener {
            Conexion.addMisionBombardeo(this, mision)
            Toast.makeText(this,"Se ha creado la misión", Toast.LENGTH_SHORT).show()
        }
    }
}