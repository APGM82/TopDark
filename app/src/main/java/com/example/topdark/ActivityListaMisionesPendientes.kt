package com.example.topdark

import Auxiliar.Conexion
import MiAdaptadorRecycler
import Modelo.Mision
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topdark.databinding.ActivityListaMisionesPendientesBinding

class ActivityListaMisionesPendientes : AppCompatActivity() {
    private lateinit var binding: ActivityListaMisionesPendientesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListaMisionesPendientesBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)
        var nombreP=intent.getStringExtra("nombrePiloto")
        //Aqui va un recyclerview
        var m=ArrayList<Mision>()

        var v= Conexion.obtenerMisionesVuelo(this)
        var b= Conexion.obtenerMisionesBombardeo(this)
        var c= Conexion.obtenerMisionesCombate(this)

        for (mision in v){
            if (mision.asignacionP.equals(nombreP)){
                Log.e("what!!!!!",mision.completada.toString()+"/ID: "+mision.id)
                if (mision.completada==0){
                    m.add(Mision(mision.id,mision.asignacionP,mision.asignacionN,mision.completada))
                }
            }
        }
        for (mision in b){
            if (mision.asignacionP.equals(nombreP)){
                if (mision.completada==0){
                    m.add(Mision(mision.id,mision.asignacionP,mision.asignacionN,mision.completada))
                }
            }
        }
        for (mision in c){
            if (mision.asignacionP.equals(nombreP)){
                if (mision.completada==0){
                    m.add(Mision(mision.id,mision.asignacionP,mision.asignacionN,mision.completada))
                }
            }
        }
        binding.rvRecicler.layoutManager = LinearLayoutManager(this)
        var miAdapter = MiAdaptadorRecycler(m, this)
        binding.rvRecicler.adapter = miAdapter
    }
}