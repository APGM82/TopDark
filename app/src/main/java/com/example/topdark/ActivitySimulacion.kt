package com.example.topdark

import Auxiliar.Conexion
import Auxiliar.Conexion.buscarNaves
import Auxiliar.Conexion.buscarPiloto
import Auxiliar.Conexion.modCompletadaBombardeo
import Auxiliar.Conexion.modCompletadaCombate
import Auxiliar.Conexion.modCompletadaVuelo
import Auxiliar.Conexion.modExpPiloto
import Modelo.MisionBombardeo
import Modelo.MisionCombate
import Modelo.MisionVuelo
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts

import com.example.topdark.databinding.ActivitySimulacionBinding
import kotlin.random.Random


class ActivitySimulacion : AppCompatActivity() {
    lateinit var binding: ActivitySimulacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimulacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                // Get data from Intent
            } else { }
        }


        var idMision=intent.getStringExtra("idMision")

        var v= Conexion.obtenerMisionesVuelo(this)
        var b= Conexion.obtenerMisionesBombardeo(this)
        var c= Conexion.obtenerMisionesCombate(this)
        var n= Conexion.obtenerNaves(this)
        var nombrePiloto=""
        for (mision in v) {
            if (mision.id.toString() == idMision) {
                nombrePiloto=mision.asignacionP
                vuelo(mision)
            }
        }
        for (mision in b) {
            if (mision.id.toString() == idMision) {
                nombrePiloto=mision.asignacionP
                bombardeo(mision)
            }
        }
        for (mision in c) {
            if (mision.id.toString() == idMision) {
                nombrePiloto=mision.asignacionP
                combate(mision)
            }
        }
        binding.btnVolverSimulacion.setOnClickListener {
            val intent = Intent(this, ActivityHomePiloto::class.java)
            intent.putExtra("nombrePiloto",nombrePiloto)
            resultLauncher.launch(intent)
            finish()
        }
    }
    fun combate(mision:MisionCombate){
        var pil:String=mision.asignacionP
        var p:Pilotos?=buscarPiloto(this,pil)
        var nav= buscarNaves(this,mision.asignacionN)
        var exp:Int= p!!.experiencia
        var objetivos=mision.cazas
        var objetivos2:Int=0
        var tiempo=objetivos*5*1000
        binding.pbSimul.max=tiempo
        while(tiempo>0){
            if (tiempo % 5000==0){
                if (exp<50){
                    var rd=Random.nextInt(0,3)
                    if (rd==0){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha derribado un objetivo"
                    }else{binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha podido derribar al objetivo"}
                }
                if (exp >=100) {
                    var rd=Random.nextInt(0,10)
                    if (rd<8){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha derribado un objetivo"
                    }else{binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha podido derribar al objetivo"}
                }
                if(exp>49&&exp<100){

                    var rd=Random.nextInt(0,1)
                    if (rd==0){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha derribado un objetivo"
                    }else{binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha podido derribar al objetivo"}
                }
            }
            binding.pbSimul.progress=binding.pbSimul.progress+1
            tiempo--
        }
        var expGanada=(objetivos2*10)
        if (nav!!.carga==1){
            expGanada=expGanada+5
            binding.txvLog.text=binding.txvLog.text.toString()+"\n Bonus de carga: +5 EXP"
        }
        if (nav.pasajeros==1){
            expGanada=expGanada+10
            binding.txvLog.text=binding.txvLog.text.toString()+"\n Bonus por pasajeros: +10 EXP"
        }
        binding.txvLog.text=binding.txvLog.text.toString()+"\n Experiencia por cazas destruidos: "+(objetivos2*10).toString()
        binding.txvExpGanada.setText(expGanada.toString())
        exp=exp+(expGanada)
        p.experiencia=exp
        modExpPiloto(this, p.nombre, p)
        if(mision.cazas < (objetivos2*2)){
            mision.completada=1
            binding.txvMisionEstado.text="¡Misión Completada!"
            binding.imgPassFail.setImageResource(R.drawable.pass)

            modCompletadaCombate(this,mision.id,mision)
        }else{
            binding.txvMisionEstado.text="¡Misión Fallada!"
            binding.imgPassFail.setImageResource(R.drawable.fail)
        }
    }
    fun bombardeo(mision:MisionBombardeo){
        var pil:String=mision.asignacionP
        var p:Pilotos?=buscarPiloto(this,pil)
        var nav= buscarNaves(this,mision.asignacionN)
        var exp:Int= p!!.experiencia
        var objetivos=mision.objetivos
        var objetivos2:Int=0
        var tiempo=objetivos*5*1000
        binding.pbSimul.max=tiempo
        while(tiempo>0){
            if (tiempo % 5000==0){
                if (exp<50){
                    var rd=Random.nextInt(0,3)
                    if (rd==0){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha destruido un objetivo"
                    }else{binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha podido destruir al objetivo"}
                }
                if (exp >=100) {
                    var rd=Random.nextInt(0,10)
                    if (rd<8){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha destruido un objetivo"
                    }else{binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha podido destruir al objetivo"}
                }
                if(exp>49&&exp<100){

                    var rd=Random.nextInt(0,1)
                    if (rd==0){
                        objetivos2++
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha destruido un objetivo"
                    }else{binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha podido destruir al objetivo"}
                }
            }
            binding.pbSimul.progress=binding.pbSimul.progress+1
            tiempo--
        }
        var expGanada=(objetivos2*5)
        if (nav!!.carga==1){
            expGanada=expGanada+5
            binding.txvLog.text=binding.txvLog.text.toString()+"\n Bonus de carga: +5 EXP"
        }
        if (nav.pasajeros==1){
            expGanada=expGanada+10
            binding.txvLog.text=binding.txvLog.text.toString()+"\n Bonus por pasajeros: +10 EXP"
        }
        binding.txvLog.text=binding.txvLog.text.toString()+"\n Experiencia por objetivos destruidos: "+(objetivos2*5).toString()
        binding.txvExpGanada.setText(expGanada.toString())
        exp=exp+(expGanada)
        p.experiencia=exp
        modExpPiloto(this, p.nombre, p)
        if(mision.objetivos < objetivos2*2){
            mision.completada=1
            binding.txvMisionEstado.text="¡Misión Completada!"
            binding.imgPassFail.setImageResource(R.drawable.pass)
            modCompletadaBombardeo(this,mision.id,mision)
        }else{
            binding.txvMisionEstado.text="¡Misión Fallada!"
            binding.imgPassFail.setImageResource(R.drawable.fail)
        }
    }
    fun vuelo(mision:MisionVuelo){
        var pil:String=mision.asignacionP
        var p:Pilotos?=buscarPiloto(this,pil)
        var nav= buscarNaves(this,mision.asignacionN)
        var exp:Int= p!!.experiencia
        var objetivos2=1
        var duracion=mision.duracion
        var tiempo=duracion*1000
        binding.pbSimul.max=tiempo
        while(tiempo>0){
            if (tiempo % 10000==0){
                if (exp<50){
                    var rd=Random.nextInt(0,2)
                    if (rd==0){

                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha superado una tormenta solar"

                    }else{
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha superado una tormenta solar"
                        objetivos2=0
                    }
                }
                if (exp >=100) {
                    var rd=Random.nextInt(0,10)
                    if (rd<9){
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha superado una tormenta solar"
                    }else{
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha superado una tormenta solar"
                        objetivos2=0
                    }
                }
                if(exp>49&&exp<100){

                    var rd=Random.nextInt(0,8)
                    if (rd<7){

                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha superado una tormenta solar"
                    }else{
                        binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha superado una tormenta solar"
                        objetivos2=0
                    }
                }
            }
            if (tiempo % 20000==0){
                var rd2=Random.nextInt(0,10)
                if (rd2<3){
                    binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto se enfrenta a un ataque"
                    if (exp<50){
                        var rd=Random.nextInt(0,10)
                        if (rd<4){

                            binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha superado el ataque"
                        }else{
                            binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha superado el ataque"
                            objetivos2=0
                        }
                    }
                    if (exp >=100) {
                        var rd=Random.nextInt(0,10)
                        if (rd<8){

                            binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha superado el ataque"
                        }else{
                            binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto no ha superado el ataque"
                            objetivos2=0
                        }
                    }
                    if(exp>49&&exp<100){

                        var rd=Random.nextInt(0,10)
                        if (rd<6){

                            binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto ha superado el ataque"
                        }else{
                            binding.txvLog.text=binding.txvLog.text.toString()+"\n El Piloto superado el ataque"
                            objetivos2=0
                        }
                    }
                }

            }

            binding.pbSimul.progress=binding.pbSimul.progress+1
            tiempo--
        }
        var expGanada=0
        if (objetivos2==1){
            expGanada=expGanada+10
            if (nav!!.carga==1){
                expGanada=expGanada+5
                binding.txvLog.text=binding.txvLog.text.toString()+"\n Bonus de carga: +5 EXP"
            }
            if (nav.pasajeros==1){
                expGanada=expGanada+10
                binding.txvLog.text=binding.txvLog.text.toString()+"\n Bonus por pasajeros: +10 EXP"
            }
        }

        binding.txvExpGanada.setText(expGanada.toString())
        exp=exp+(expGanada)
        p.experiencia=exp
        modExpPiloto(this, p.nombre, p)
        Log.e("obj",objetivos2.toString())
        if(objetivos2==1){
            mision.completada=1
            binding.txvMisionEstado.text="¡Misión Completada!"
            binding.imgPassFail.setImageResource(R.drawable.pass)
            modCompletadaVuelo(this,mision.id,mision,objetivos2)
            Log.e("obj","Modificado")
        }else{
            binding.txvMisionEstado.text="¡Misión Fallada!"
            binding.imgPassFail.setImageResource(R.drawable.fail)
        }
    }
}