package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.topdark.databinding.ActivityCrearMisionBinding

class ActivityCrearMision : AppCompatActivity() {
    lateinit var binding: ActivityCrearMisionBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get data from Intent
        } else { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearMisionBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        binding.btnVolverCrearMision.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnCrearVuelo.setOnClickListener {
            val intent = Intent(this, ActivityVuelo::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnCrearBombardeo.setOnClickListener {
            val intent = Intent(this, ActivityBombardeo::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnCrearCombate.setOnClickListener {
            val intent = Intent(this, ActivityCombate::class.java)
            resultLauncher.launch(intent)
        }

    }
}