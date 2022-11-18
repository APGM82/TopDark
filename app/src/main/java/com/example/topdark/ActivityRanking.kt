package com.example.topdark

import Adaptadores.MiAdaptadorRecycler2
import Auxiliar.Conexion
import Auxiliar.Conexion.obtenerPilotos
import Modelo.Mision
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topdark.databinding.ActivityCrearMisionBinding
import com.example.topdark.databinding.ActivityRankingBinding

class ActivityRanking : AppCompatActivity() {
    lateinit var binding: ActivityRankingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        var pil=ArrayList<Pilotos>()

        var p= obtenerPilotos(this)

        for (piloto in p){
            if (piloto.nombre!="Vader"){
                pil.add(piloto)
            }
        }
        binding.btnVolverRanking.setOnClickListener{
                val intent = Intent()
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
        }
        binding.rvPilotos.layoutManager = LinearLayoutManager(this)
        var miAdapter = MiAdaptadorRecycler2(pil, this)
        binding.rvPilotos.adapter = miAdapter

    }
}