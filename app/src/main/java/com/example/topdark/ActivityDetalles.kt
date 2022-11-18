package com.example.topdark

import Auxiliar.Conexion
import Auxiliar.Conexion.buscarNaves
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.topdark.databinding.ActivityDetallesBinding

class ActivityDetalles : AppCompatActivity() {
    lateinit var binding: ActivityDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallesBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        binding.txvMinutos.isVisible=false
        binding.txvMinutos2.isVisible=false
        binding.txvObjetivos.isVisible=false
        binding.txvObjetivos2.isVisible=false
        binding.txvCazasDetalles.isVisible=false
        binding.txvCazasDetalles2.isVisible=false

        var idMision=intent.getStringExtra("id")
        var tipoMision=""
        var nombrePiloto=""
        var matriculaNave=""
        var duracion=""
        var objetivos=""
        var cazas=""

        var v= Conexion.obtenerMisionesVuelo(this)
        var b= Conexion.obtenerMisionesBombardeo(this)
        var c= Conexion.obtenerMisionesCombate(this)
        var n= Conexion.obtenerNaves(this)
        var p= Conexion.obtenerPilotos(this)

        for (mision in v) {
            if (mision.id.toString() == idMision) {
                tipoMision="Vuelo"
                nombrePiloto=mision.asignacionP
                matriculaNave=mision.asignacionN
                duracion=mision.duracion.toString()
                binding.txvMinutos.isVisible=true
                binding.txvMinutos2.isVisible=true
            }
        }
        for (mision in b) {
            if (mision.id.toString() == idMision) {
                tipoMision="Bombardeo"
                nombrePiloto=mision.asignacionP
                matriculaNave=mision.asignacionN
                objetivos=mision.objetivos.toString()
                binding.txvObjetivos.isVisible=true
                binding.txvObjetivos2.isVisible=true
            }
        }
        for (mision in c) {
            if (mision.id.toString() == idMision) {
                tipoMision="Combate"
                nombrePiloto=mision.asignacionP
                matriculaNave=mision.asignacionN
                cazas=mision.cazas.toString()
                binding.txvCazasDetalles.isVisible=true
                binding.txvCazasDetalles2.isVisible=true
            }
        }
        var nave=buscarNaves(this,matriculaNave)
        binding.txvId2.text=idMision
        binding.txvTipoMision2.text=tipoMision

        if (nave!!.pasajeros==1){
            binding.txvPasajeros2.text="Si"
        }else{binding.txvPasajeros2.text="No"}
        if (nave!!.carga==1){
            binding.txvCarga2.text="Si"
        }else{binding.txvCarga2.text="No"}

        binding.txvPiloto2.text=nombrePiloto
        binding.txvNave2.text=nave.matricula
        binding.txvTipoNave2.text=nave.tipo
        binding.txvMinutos2.text=duracion
        binding.txvObjetivos2.text=objetivos
        binding.txvCazasDetalles2.text=cazas

        binding.btnVolverDetalles.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }



    }
}