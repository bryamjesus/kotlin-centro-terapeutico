package com.example.centro_terapeutico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.RuntimeException
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class Email : AppCompatActivity() {
    private var btnSend:ImageButton?= null
    private var txtMessage:EditText?=null
    private val emailEmpresa:String = "bryamtalledog@gmail.com"
    private var rbGroup:RadioGroup?= null
    private var asunto:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        rbGroup = findViewById(R.id.rbGroup)
        rbGroup!!.check(R.id.rbSugerencia)
        btnSend = findViewById(R.id.btnSend)
        txtMessage = findViewById(R.id.txtMessage)

        val btnMessage = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        btnMessage.setOnClickListener(){
            val message = Intent(this,SendMessage::class.java,)
            startActivity(message);
        }

        btnSend!!.setOnClickListener {
            val username = "pruebabryam@gmail.com"
            val password = "@GPtrompeta302212@"

            val messageToSend: String = txtMessage!!.text.toString()
            val props = Properties()
            props["mail.smtp.auth"] = true
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.smtp.host"] = "smtp.gmail.com"
            props["mail,smtp.port"] = "587"//587
            val session = Session.getInstance(props,
                object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })
            try {
                val message: Message = MimeMessage(session)
                message.setFrom(InternetAddress(username))
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailEmpresa)
                )
                message.subject = asunto
                message.setText(messageToSend)

                Transport.send(message)
                Toast.makeText(applicationContext, "Enviado correctamente", Toast.LENGTH_LONG).show()
            } catch (e: MessagingException) {
                throw RuntimeException(e)
            }
        }
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun onRadioButtonClicked(view: View) {
        btnSend = findViewById(R.id.btnSend)
        txtMessage = findViewById(R.id.txtMessage)
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.rbReclamo ->
                    if (checked) {
                        txtMessage!!.setHint(R.string.email_reclamo)
                        asunto = "Reclamo"
                    }
                R.id.rbSugerencia ->
                    if (checked) {
                        txtMessage!!.setHint(R.string.email_sugerencia)
                        asunto="Sugerencia"
                    }
            }
        }
    }
}