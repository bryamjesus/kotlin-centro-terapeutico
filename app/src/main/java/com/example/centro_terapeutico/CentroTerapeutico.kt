package com.example.centro_terapeutico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class CentroTerapeutico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centro_terapeutico)
        val tvUsuario = findViewById<TextView>(R.id.tvUsuario);
        val  btnMapa = findViewById<ImageButton>(R.id.btnMapa);
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        var usuario = intent.getStringExtra(Constante.usuario);
        val btnMessage = findViewById<ImageButton>(R.id.btnMessage)


        tvUsuario.text = usuario;
        btnMapa.setOnClickListener(){
            val map = Intent(this, Mapa::class.java)
            startActivity(map);
        }
        btnRegistro.setOnClickListener(){
            val re = Intent(this, RegistroActivity::class.java)
            startActivity(re);
        }

        btnMessage.setOnClickListener(){
            val me= Intent(this, Email::class.java)
            startActivity(me);
        }
    }
}