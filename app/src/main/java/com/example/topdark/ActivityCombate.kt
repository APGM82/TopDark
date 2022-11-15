package com.example.topdark

import Auxiliar.Conexion
import Modelo.MisionBombardeo
import Modelo.MisionCombate
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.topdark.databinding.ActivityBombardeoBinding
import com.example.topdark.databinding.ActivityCombateBinding

class ActivityCombate : AppCompatActivity() {
    lateinit var binding: ActivityCombateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCombateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sumamisiones:Int=0

        var v= Conexion.obtenerMisionesVuelo(this)
        var c= Conexion.obtenerMisionesCombate(this)
        var b= Conexion.obtenerMisionesBombardeo(this)
        sumamisiones=v.size+c.size+b.size

        var mision: MisionCombate =  MisionCombate(sumamisiones,binding.txtCazas.text.toString().toInt(),"","",false)


        binding.btnVolverMisionCombate.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnMisionOkCombate.setOnClickListener {
            Conexion.addMisionCombate(this, mision)
            Toast.makeText(this,"Se ha creado la misión", Toast.LENGTH_SHORT).show()
        }
    }
}