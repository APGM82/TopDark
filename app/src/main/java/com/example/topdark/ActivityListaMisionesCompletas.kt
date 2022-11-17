package com.example.topdark

import Auxiliar.Conexion
import MiAdaptadorRecycler
import Modelo.Mision
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topdark.databinding.ActivityListaMisionesCompletasBinding

class ActivityListaMisionesCompletas : AppCompatActivity() {
    private lateinit var binding:ActivityListaMisionesCompletasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListaMisionesCompletasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombreP=intent.getStringExtra("nombrePiloto")
        //Aqui va un recyclerview
        var m=ArrayList<Mision>()

        var v= Conexion.obtenerMisionesVuelo(this)
        var b= Conexion.obtenerMisionesBombardeo(this)
        var c= Conexion.obtenerMisionesCombate(this)

        for (mision in v){
            if (mision.asignacionP.equals(nombreP)){
                if (mision.completada==1){
                    m.add(Mision(mision.id,mision.asignacionP,mision.asignacionN,mision.completada))
                }
            }
        }
        for (mision in b){
            if (mision.asignacionP.equals(nombreP)){
                if (mision.completada==1){
                    m.add(Mision(mision.id,mision.asignacionP,mision.asignacionN,mision.completada))
                }
            }
        }
        for (mision in c){
            if (mision.asignacionP.equals(nombreP)){
                if (mision.completada==1){
                    m.add(Mision(mision.id,mision.asignacionP,mision.asignacionN,mision.completada))
                }
            }
        }
        binding.rvRecicler.layoutManager = LinearLayoutManager(this)
        var miAdapter = MiAdaptadorRecycler(m, this)
        binding.rvRecicler.adapter = miAdapter
    }
}