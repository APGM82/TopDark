package com.example.topdark

import Auxiliar.Conexion
import Modelo.Pilotos
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.topdark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get data from Intent
        } else { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        binding.btnConectar.setOnClickListener {
            var p: Pilotos? = Conexion.buscarPiloto(this, binding.txtUsername.text.toString())

            if (p != null) {

                //Si es vader
                if (binding.txtUsername.text.toString() == "Vader" && binding.txtPassword.text.toString().equals(p.password)) {
                    val intent = Intent(this, ActivityVader::class.java)
                    resultLauncher.launch(intent)
                //Si es usuario con contraseña NO predefinida
                } else if (binding.txtPassword.text.toString().equals(p.password) && binding.txtPassword.text.toString() != "password") {
                    val intent = Intent(this, ActivityHomePiloto::class.java)
                    intent.putExtra("nombrePiloto",binding.txtUsername.text.toString())
                    resultLauncher.launch(intent)
                //Si es usuario con contraseña predefinida
                } else if (binding.txtPassword.text.toString().equals(p.password) && binding.txtPassword.text.toString().equals("password")) {
                    val intent = Intent(this, ActivityCambioPassword::class.java)
                    intent.putExtra("nombrePiloto",p.nombre)
                    resultLauncher.launch(intent)
                } else {
                    Toast.makeText(this, "Los datos no son válidos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No hay ningun piloto con esos datos", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}