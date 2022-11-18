package com.example.topdark

import Auxiliar.Conexion.buscarPiloto
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topdark.databinding.ActivityAltaPilotoBinding
import com.example.topdark.databinding.ActivityPerfilBinding

class ActivityPerfil : AppCompatActivity() {
    lateinit var binding: ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        var nom=intent.getStringExtra("nombrePiloto")

        var p:Pilotos?=buscarPiloto(this,nom!!)
        var textoRango:String=""
        if (p!!.experiencia<50){
            textoRango="Novato"
        }
        if (p.experiencia in 50..100){
            textoRango="Intermedio"
        }
        if (p!!.experiencia>100){
            textoRango="Experto"
        }
        binding.txvPerfilNombreResultado.text=p!!.nombre
        binding.txvPerfilEdadResultado.text=p!!.edad.toString()
        binding.txvPerfilExperienciaResultado.text=p!!.experiencia.toString()
        binding.txvPerfilRangoResultado.text=textoRango
        if(p?.foto!=null){
            var bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null).toString() + "/"+nom+".jpg");
            binding.imgPerfil.setImageBitmap(bitmap1)
        }

        binding.btnVolverPerfil.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
    }
}