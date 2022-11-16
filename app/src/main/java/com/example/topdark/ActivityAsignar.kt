package com.example.topdark


import Auxiliar.Conexion.modAsignacionesBombardeoNave
import Auxiliar.Conexion.modAsignacionesBombardeoPiloto
import Auxiliar.Conexion.modAsignacionesCombateNave
import Auxiliar.Conexion.modAsignacionesCombatePiloto
import Auxiliar.Conexion.modAsignacionesVueloNave
import Auxiliar.Conexion.modAsignacionesVueloPiloto
import Auxiliar.Conexion.obtenerMisionesBombardeo
import Auxiliar.Conexion.obtenerMisionesCombate
import Auxiliar.Conexion.obtenerMisionesVuelo
import Auxiliar.Conexion.obtenerNaves
import Auxiliar.Conexion.obtenerPilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.topdark.databinding.ActivityAsignarBinding

class ActivityAsignar : AppCompatActivity() {
    lateinit var binding: ActivityAsignarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsignarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var misionId=arrayListOf<String>()
        var pilotosNombre= arrayListOf<String>()
        var navesMatricula= arrayListOf<String>()

        var v=obtenerMisionesVuelo(this)
        var b= obtenerMisionesBombardeo(this)
        var c= obtenerMisionesCombate(this)
        var n= obtenerNaves(this)
        var p= obtenerPilotos(this)



        binding.btnAsignarTodo.isVisible=false
        for (mision in v){
            misionId.add(mision.id.toString())
        }
        for (mision in b){
            misionId.add(mision.id.toString())
        }
        for (mision in c){
            misionId.add(mision.id.toString())
        }
        for (nave in n){
            navesMatricula.add(nave.matricula)
        }
        for (piloto in p){
            pilotosNombre.add(piloto.nombre)
        }
        val adaptadorVuelo= ArrayAdapter(this,R.layout.item_lista,R.id.txtItem,misionId)
        binding.spnMision.adapter=adaptadorVuelo

        val adaptadorNaves= ArrayAdapter(this,R.layout.item_lista,R.id.txtItem,navesMatricula)
        binding.spnNave.adapter=adaptadorNaves

        val adaptadorPilotos= ArrayAdapter(this,R.layout.item_lista,R.id.txtItem,pilotosNombre)
        binding.spnPiloto.adapter=adaptadorPilotos

        binding.btnArraigar.setOnClickListener {
            binding.txvMisionArraigada.text=binding.spnMision.selectedItem.toString()
            binding.txvNaveArraigada.text=binding.spnNave.selectedItem.toString()
            binding.txvPilotoArraigado.text=binding.spnPiloto.selectedItem.toString()
            binding.btnAsignarTodo.isVisible=true
        }
        binding.btnVolverAsignar.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnAsignarTodo.setOnClickListener {
            //COMPRUEBO EL TIPO DE LA MISION PARA MODIFICARLA EN LA BASE DE DATOS CON LAS ASIGNACIONES DE PILOTO Y NAVE
            //SI ES DE VUELO
            for (mision in v){
                if (mision.id.toString()==binding.txvMisionArraigada.text.toString()){
                mision.asignacionP=binding.txvPilotoArraigado.text.toString()
                mision.asignacionN=binding.txvNaveArraigada.text.toString()
                modAsignacionesVueloPiloto(this,binding.txvMisionArraigada.text.toString().toInt(),mision)
                modAsignacionesVueloNave(this,binding.txvMisionArraigada.text.toString().toInt(),mision)
                    Toast.makeText(this,"Mision de vuelo "+binding.txvMisionArraigada.text.toString()+" asignada a "+binding.txvPilotoArraigado.text.toString()+" con la nave "+binding.txvNaveArraigada.text.toString(),Toast.LENGTH_SHORT).show()
                }
            }

            //SI ES DE BOMBARDEO
            for (mision in b){if (mision.id.toString()==binding.txvMisionArraigada.text.toString()){
                mision.asignacionP=binding.txvPilotoArraigado.text.toString()
                mision.asignacionN=binding.txvNaveArraigada.text.toString()
                modAsignacionesBombardeoPiloto(this,binding.txvMisionArraigada.text.toString().toInt(),mision)
                modAsignacionesBombardeoNave(this,binding.txvMisionArraigada.text.toString().toInt(),mision)
                Toast.makeText(this,"Mision de bombardeo "+binding.txvMisionArraigada.text.toString()+" asignada a "+binding.txvPilotoArraigado.text.toString()+" con la nave "+binding.txvNaveArraigada.text.toString(),Toast.LENGTH_SHORT).show()
                }
            }
            //SI ES DE COMBATE
            for (mision in c) {
                if (mision.id.toString() == binding.txvMisionArraigada.text.toString()) {
                mision.asignacionP=binding.txvPilotoArraigado.text.toString()
                mision.asignacionN=binding.txvNaveArraigada.text.toString()
                modAsignacionesCombatePiloto(this,binding.txvMisionArraigada.text.toString().toInt(),mision)
                modAsignacionesCombateNave(this,binding.txvMisionArraigada.text.toString().toInt(),mision)
                Toast.makeText(this,"Mision de combate "+binding.txvMisionArraigada.text.toString()+" asignada a "+binding.txvPilotoArraigado.text.toString()+" con la nave "+binding.txvNaveArraigada.text.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
