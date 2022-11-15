package com.example.topdark

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.topdark.databinding.ActivityVaderBinding

class ActivityVader : AppCompatActivity() {
    lateinit var binding: ActivityVaderBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get data from Intent
        } else { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.btnAltaPiloto.setOnClickListener{
            val intent = Intent(this, ActivityAltaPiloto::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnAltaNave.setOnClickListener{
            val intent = Intent(this, ActivityAltaNave::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnCrearMision.setOnClickListener{
            val intent = Intent(this, ActivityCrearMision::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnRanking.setOnClickListener{
            val intent = Intent(this, ActivityRanking::class.java)
            resultLauncher.launch(intent)
        }
    }
}