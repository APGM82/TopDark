package com.example.topdark

import Auxiliar.Conexion
import Auxiliar.Conexion.modFoto
import Modelo.Pilotos
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.topdark.databinding.ActivityAltaNaveBinding
import com.example.topdark.databinding.ActivityHomePilotoBinding
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class ActivityHomePiloto : AppCompatActivity() {
    private val cameraRequest = 1888

    lateinit var binding: ActivityHomePilotoBinding

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get data from Intent
        } else { }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePilotoBinding.inflate(layoutInflater)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(binding.root)

        var nom:String=intent.getStringExtra("nombrePiloto")!!
        var p: Pilotos?= Conexion.buscarPiloto(this, nom)

        if(p?.foto!=null){
            var bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null).toString() + "/"+nom+".jpg");
            binding.imageView.setImageBitmap(bitmap1)
        }

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)

        binding.btnVolverHomePiloto.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            resultLauncher.launch(intent)
            finish()
        }
        binding.btnCambiarFoto.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequest)
            p!!.foto=nom+".jpg"
            modFoto(this,nom,p!!)
        }
        binding.btnVerPerfil.setOnClickListener {
            val intent = Intent(this, ActivityPerfil::class.java)
            intent.putExtra("nombrePiloto",p!!.nombre)
            resultLauncher.launch(intent)
        }
        binding.btnListadoMisiones.setOnClickListener {
            val intent = Intent(this, ActivityListaMisionesPendientes::class.java)
            intent.putExtra("nombrePiloto",p!!.nombre)
            resultLauncher.launch(intent)
        }
        binding.btnMisionesRealizadas.setOnClickListener {
            val intent = Intent(this, ActivityListaMisionesCompletas::class.java)
            intent.putExtra("nombrePiloto",p!!.nombre)
            resultLauncher.launch(intent)
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                binding.imageView.setImageBitmap(photo)
                var nombre:String=intent.getStringExtra("nombrePiloto")!!
                var fotoFichero = File(getExternalFilesDir(null), "/$nombre.jpg")
                var uri = Uri.fromFile(fotoFichero)
                var fileOutStream = FileOutputStream(fotoFichero)
                photo.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream);
                fileOutStream.flush();
                fileOutStream.close();


            }
        }catch(e: Exception){
            Toast.makeText(this,"Error al tomar la foto",Toast.LENGTH_SHORT).show()
        }

    }
}