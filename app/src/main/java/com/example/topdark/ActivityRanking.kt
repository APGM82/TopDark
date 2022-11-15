package com.example.topdark

import Auxiliar.Conexion.obtenerPilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.topdark.databinding.ActivityCrearMisionBinding
import com.example.topdark.databinding.ActivityRankingBinding

class ActivityRanking : AppCompatActivity() {
    lateinit var binding: ActivityRankingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolverRanking.setOnClickListener{
                val intent = Intent()
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
        }

        val adaptador= ArrayAdapter(this,R.layout.item_lista,R.id.txtItem,obtenerPilotos(this))
        binding.lstLista.adapter=adaptador    }
}