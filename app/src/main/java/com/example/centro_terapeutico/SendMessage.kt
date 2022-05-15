package com.example.centro_terapeutico

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.lang.Exception

class SendMessage : AppCompatActivity() {
    private var message: EditText?= null
    private var number: EditText?= null
    private var btnMessage: Button?= null
    private var btnWhatsapp: Button?= null
    private var uri:String = "whatsapp://send?phone=51"
    private var linkCelular:String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        message = findViewById(R.id.etMensaje);
        number = findViewById(R.id.etNumero);

        //BOTONES
        btnMessage = findViewById(R.id.btnSMS)
        btnWhatsapp = findViewById(R.id.btnWhatsapp)

        //PERMISOS
        //if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.))

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),1)
        }




        //EVENTOS
        btnMessage!!.setOnClickListener{
            try {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    number!!.text.toString(),
                    null,
                    message!!.text.toString(),
                    null,
                    null
                )
                Toast.makeText(this, "MSJ Enviado", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Error SMS...\n Ingrese un Numero Valido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }




        btnWhatsapp!!.setOnClickListener{
            if(message!!.text.isEmpty()){
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND;
                sendIntent.putExtra(Intent.EXTRA_TEXT, message!!.text.toString());
                sendIntent.type = "text/plain";
                sendIntent.setPackage("com.whatsapp")
                startActivity(sendIntent);
            }
            else{
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_VIEW;
                linkCelular = uri+number!!.text.toString()+"&text="+message!!.text.toString()
                sendIntent.data = Uri.parse(linkCelular)
                startActivity(sendIntent);
            }
        }
    }
}