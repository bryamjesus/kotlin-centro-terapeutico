package com.example.centro_terapeutico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var usuario:String= "admin"
        var contrasena:String ="admin"
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val etError  = findViewById<TextView>(R.id.etError)

        btnIngresar.setOnClickListener {
            etError.text = " "
            val textoUsuario = findViewById<EditText>(R.id.etUsuario)
            val textoContrasenia = findViewById<EditText>(R.id.etContrasenia)
            val usuario1 = textoUsuario.text.toString()
            val contrasenia1 = textoContrasenia.text.toString()
            if(usuario1.isNotEmpty() || contrasenia1.isNotEmpty()){
                if(usuario1 == usuario && contrasenia1== contrasena){
                    var centro = Intent(this, CentroTerapeutico::class.java)
                    centro.putExtra(Constante.usuario,usuario)
                    startActivity(centro)
                }
                else{
                    etError.text = "Ingrese bien los datos  "
                }
            }
            else{
                etError.text = "Ingrese los Datos"
            }
        }
    }
}