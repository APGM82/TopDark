package com.example.topdark

import Auxiliar.Conexion.buscarPiloto
import Auxiliar.Conexion.modExpPiloto
import Modelo.MisionCombate
import Modelo.Pilotos
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.topdark.databinding.ActivitySimulacionBinding
import kotlin.random.Random


class ActivitySimulacion : AppCompatActivity() {
    lateinit var binding: ActivitySimulacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimulacionBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    fun combate(mision:MisionCombate){
        var pil:String=mision.asignacionP
        var p:Pilotos?=buscarPiloto(this,pil)
        var exp:Int= p!!.experiencia
        var objetivos=mision.objetivos
        var objetivos2:Int=0
        var tiempo=objetivos*5*1000
        while(tiempo>0){
            if (tiempo % 5000==0){
                if (exp<50){
                    var rd=Random.nextInt(0-3)
                    if (rd==0){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha derribado un objetivo"
                    }
                }
                if (exp >=100) {
                    var rd=Random.nextInt(0-10)
                    if (rd>8){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha derribado un objetivo"
                    }
                }
                if(exp>49&&exp<100){

                    var rd=Random.nextInt(0-1)
                    if (rd==0){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha derribado un objetivo"
                    }
                }
            }
            tiempo--
        }
        binding.txvExpGanada.setText((objetivos2*10).toString())
        exp=exp+(objetivos2*10)
        p.experiencia=exp
        modExpPiloto(this, p.nombre, p)
    }
}