package com.robe.consumptionapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.robe.consumptionapi.extra.iniciarSesion
import com.robe.consumptionapi.extra.validarSesion
import org.json.JSONObject

class Registro : AppCompatActivity() {

    private var TAG = Login::class.qualifiedName

    private lateinit var til_correo: TextInputLayout
    private lateinit var tiet_correo: TextInputEditText
    private lateinit var til_contrasena: TextInputLayout
    private lateinit var tiet_contrasena: TextInputEditText
    private lateinit var btn_ingrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {

//        if(validarSesion(applicationContext)){
//            lanzarActivity()
//        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        init()
    }

    fun init() {
        til_correo = findViewById(R.id.til_correo)
        tiet_correo = findViewById(R.id.tiel_correo)
        til_contrasena = findViewById(R.id.til_contrasena)
        tiet_contrasena = findViewById(R.id.tiel_contrasena)
        btn_ingrear = findViewById(R.id.btn_ingresar)
        btn_ingrear.setOnClickListener {
            val cola = Volley.newRequestQueue(applicationContext)
            val json = JSONObject()
            json.put("email", tiet_correo.text)
            json.put("password", tiet_contrasena.text)
            json.put("device_name", "User's phone")
            val peticion = JsonObjectRequest(
                Request.Method.POST,
                getString(R.string.url_servidor) + getString(R.string.api_login), json,
                { response ->
                    val jsonObject = JSONObject(response.toString())
                    iniciarSesion((applicationContext), jsonObject)
                    if (validarSesion(applicationContext)) {
                        lanzarActivity()
                    }
                    //Log.d(TAG, response.toString() )
                },
                { error ->
                    Log.e(TAG, error.toString())
                })
            cola.add(peticion)
        }
    }

    fun lanzarActivity(){
        val intent = Intent(this, MainActivity::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Para dejar solo la acivity que estoy llamando
        startActivity(intent)
        finish()
    }
}