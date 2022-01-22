package com.robe.consumptionapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {

    private lateinit var til_correo: TextInputLayout
    private lateinit var tiel_correo: TextInputEditText
    private lateinit var til_contrasena: TextInputLayout
    private lateinit var tiel_contrasena: TextInputEditText
    private lateinit var btn_ingrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun init(){
        til_correo = findViewById(R.id.til_correo)
        tiel_correo = findViewById(R.id.tiel_correo)
        til_contrasena = findViewById(R.id.til_contrasena)
        tiel_contrasena = findViewById(R.id.tiel_contrasena)
        btn_ingrear = findViewById(R.id.btn_ingresar)
        btn_ingrear.setOnClickListener{
            val cola = Volley.newRequestQueue(applicationContext)
           // val peticion = JsonObjectRequest(Request.Method.POST)
        }
    }

}