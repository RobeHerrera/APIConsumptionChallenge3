package com.robe.consumptionapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.robe.consumptionapi.extra.*

class MainActivity : AppCompatActivity() {

    private lateinit var btn_cerrar_sesion: Button
    private var TAG = Login::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        btn_cerrar_sesion = findViewById(R.id.btn_cerrar_sesion)
        btn_cerrar_sesion.setOnClickListener {
            eliminarSesion(applicationContext)
            startActivity(Intent(this,Login::class.java))
            finish()

        /*val cola = Volley.newRequestQueue(applicationContext)
            val peticion = object : JsonObjectRequest(
                Request.Method.POST,
                getString(R.string.url_servidor)+getString(R.string.api_logout),
                null,
                { reponse ->
                    Log.d(TAG, "Todo salio bien")
//                    eliminarSesion(applicationContext)
//                    startActivity((Intent(this,Login::class.java)))
//                    finish()
                },
                { error ->
                    Log.e(TAG, error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    println("Bearer "+ obtenerDeSesion(applicationContext, "token"))
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization","Bearer "+ obtenerDeSesion(applicationContext, "token"))
//                    headers["Authorization"] =
//                        "Bearer" + obtenerDeSesion(applicationContext, "token")
                    return headers
                }
            }
//            val pericionDos = JsonObjectRequest(
//                Request.Method.POST,
//                getString(R.string.api_logout),
//                null,
//                { reponse ->
//                },
//                { error ->
//                    Log.e(TAG, error.toString())
//                })
//            val headers = HashMap<String,String>()
//            headers["Authorization"] = "Bearer " + obtenerDeSesion(applicationContext, "token")
//            peticionDos.headers = headers

            cola.add(peticion)*/
        }


    }
}