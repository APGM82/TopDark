package com.example.topdark

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
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
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        var mediaPlayer: MediaPlayer = MediaPlayer.create(this,R.raw.breathing)
        mediaPlayer.start();

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            resultLauncher.launch(intent)
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
        binding.btnAsignar.setOnClickListener {
            val intent = Intent(this, ActivityAsignar::class.java)
            resultLauncher.launch(intent)
        }
    }
}