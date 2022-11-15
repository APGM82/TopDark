package com.example.topdark

import Auxiliar.Conexion.buscarPiloto
import Auxiliar.Conexion.modPasswd
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.topdark.databinding.ActivityCambioPasswordBinding

class ActivityCambioPassword : AppCompatActivity() {
    lateinit var binding: ActivityCambioPasswordBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get data from Intent
        } else { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambioPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nom:String=intent.getStringExtra("nombrePiloto")!!
        var p:Pilotos?=buscarPiloto(this,nom)

        binding.txvNombrePasswd.text=nom

        binding.btnCambiarPassword.setOnClickListener{
            p!!.password=binding.txtNewPassword.text.toString()
            modPasswd(this,nom,p!!)
            Toast.makeText(this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show()
        }
        binding.btnContinuar.setOnClickListener {
            val intent = Intent(this, ActivityHomePiloto::class.java)
            intent.putExtra("nombrePiloto",p!!.nombre)
            resultLauncher.launch(intent)
        }

    }
}