package com.example.centro_terapeutico


import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class RegistroActivity : AppCompatActivity() {
    private val token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImJyeWplczMwMDNAZ21haWwuY29tIn0.n_xzEqqBuhDW-g3E-kMtiAWr2eU1bn6RLUnhXV_5oZk"
    private var url_inicio_dni ="https://dniruc.apisperu.com/api/v1/dni/"
    private var url_inicio_ruc ="https://dniruc.apisperu.com/api/v1/ruc/"

    /*DNI*/
    private var txtDniRuc:EditText? =null
    private var txtNombre:EditText? = null
    private var txtapellidoP:EditText? = null
    private var txtApellidoM:EditText? = null


    /*RUC*/
    private var txtRazonSocial:EditText?=null
    private var txtDomicilio:EditText?= null
    private var txtDep:EditText? = null
    private var txtProvincia:EditText? = null
    private var txtDistrito:EditText? = null
    private var txtEstado:EditText? = null
    private var txtCondicion:EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val rbDni = findViewById<RadioButton>(R.id.rbDni)
        val rbRuc = findViewById<RadioButton>(R.id.rbRuc)
        val btnBuscar = findViewById<ImageButton>(R.id.btnBuscar)

        txtDniRuc = findViewById(R.id.txtNumero)
        btnBuscar.setOnClickListener() {
            if(txtDniRuc!!.text.length != 8 && rbDni.isChecked){
                Toast.makeText(this, "Numero de DNI invalido",Toast.LENGTH_LONG).show()
            }else if(txtDniRuc!!.text.length != 11 && rbRuc.isChecked){
                Toast.makeText(this, "Numero de RUC invalido",Toast.LENGTH_LONG).show()
            }else if(txtDniRuc!!.text.length == 8 && rbDni.isChecked){
                consultardni(txtDniRuc!!.text.toString())
                //IniciarDialog();
                var url = url_inicio_dni+txtDniRuc!!.text.toString()+token
                //Toast.makeText(this, "${url}",Toast.LENGTH_LONG).show()
            }else{
                consultarRuc(txtDniRuc!!.text.toString())
            }
        }
    }

    private fun consultarRuc(ruc: String) {
        val queue = Volley.newRequestQueue(this)
        val url = url_inicio_ruc+ruc+token;
        txtRazonSocial = findViewById(R.id.txtRazonSocial)
        txtDomicilio = findViewById(R.id.txtDomicilio)
        txtDep = findViewById(R.id.txtDep)
        txtProvincia = findViewById(R.id.txtProvincia)
        txtDistrito = findViewById(R.id.txtDistrito)
        txtEstado = findViewById(R.id.txtEstado)
        txtCondicion = findViewById(R.id.txtCondicion)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,
            null,
            { response ->
                var rs:String = response.getString("razonSocial")
                var direction: String= response.getString("direccion")
                var dep:String = response.getString("departamento")
                var provincia:String = response.getString("provincia")
                var distrito:String = response.getString("distrito")
                var estado:String = response.getString("estado")
                var condicion:String = response.getString("condicion")
                txtRazonSocial!!.setText(rs);
                txtDomicilio!!.setText(direction)
                txtDep!!.setText(dep);
                txtProvincia!!.setText(provincia);
                txtDistrito!!.setText(distrito);
                txtEstado!!.setText(estado);
                txtCondicion!!.setText(condicion)
            },
            { error ->  }
        )
        queue.add(jsonObjectRequest)
    }

    fun onRadioButtonClicked(view: View) {
        val layout_dni = findViewById<LinearLayout>(R.id.layout_dni)
        val layout_ruc = findViewById<LinearLayout>(R.id.layout_ruc)
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbDni ->
                    if (checked) {
                        // Pirates are the best
                        layout_dni.isVisible = true
                        layout_ruc.isVisible = false
                        txtDniRuc!!.setText("")
                    }
                R.id.rbRuc ->
                    if (checked) {
                        layout_dni.isVisible = false
                        layout_ruc.isVisible = true
                        txtDniRuc!!.setText("")
                        // Ninjas rule
                    }
            }
        }
    }

    private fun consultardni(dni:String) {
        val queue = Volley.newRequestQueue(this)
        var url = url_inicio_dni+dni+token
        //Toast.makeText(this, "${url}",Toast.LENGTH_LONG).show()
        txtNombre = findViewById(R.id.txtNombre)
        txtapellidoP = findViewById(R.id.txtPaterno)
        txtApellidoM = findViewById(R.id.txtMaterno);
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,
            null,
            { response ->
                var nombres:String = response.getString("nombres")
                var apellidoP: String= response.getString("apellidoPaterno")
                var apellidoM:String = response.getString("apellidoMaterno")
                txtNombre!!.setText(nombres);
                txtapellidoP!!.setText(apellidoP)
                txtApellidoM!!.setText(apellidoM);
            },
            { error ->  }
        )
        queue.add(jsonObjectRequest)
    }

    private fun IniciarDialog(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
    }
}

/*
* /*val groubtn = findViewById<RadioGroup>(R.id.rbGroup)
        var sa= groubtn.checkedRadioButtonId()

            if(rbDni.isChecked){
                Toast.makeText(this, "123",Toast.LENGTH_LONG).show()
                layout_dni.isVisible = true
                layout_ruc.isVisible = false
            }
            if(rbRuc.isChecked){
                Toast.makeText(this, "345",Toast.LENGTH_LONG).show()
                layout_dni.isVisible = false
                layout_ruc.isVisible = true
            }*/

        //Toast.makeText(this, "${dfsa}",Toast.LENGTH_LONG).show()

        //20445226590*/

/*fun Consultar(){
        val postRequest = Volley.newRequestQueue(this)
        val requerimiento = JsonArrayRequest(Request.Method.GET,
        api + txtNumDoc.text,
        null,
        Response.Listener {
            @Override

        })
    }*/