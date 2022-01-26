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
import com.google.gson.Gson
import com.robe.consumptionapi.dataclass.Empleado
import com.robe.consumptionapi.extra.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.lang.Exception

class Login : AppCompatActivity() {

    private var TAG = Login::class.qualifiedName

    private lateinit var til_correo: TextInputLayout
    private lateinit var tiet_correo: TextInputEditText
    private lateinit var til_contrasena: TextInputLayout
    private lateinit var tiet_contrasena: TextInputEditText
    private lateinit var btn_ingrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        eliminarSesion(applicationContext)
        if (validarSesion(applicationContext)) {
            lanzarActivity()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        pruebaJsonObject()
    }

    fun pruebaJsonObject() {
        // Queremos pasar una cadena de carteres
        val json = """
        {
            "plain-text-token":"179|ysUKKNxGtIDAEkBEndknTjCcE2DZyRRDgXXjlEPz"
        }
    """.trimIndent()
        val jsonObject = JSONObject(json)
//        print(jsonObject.toString())
        Log.d(TAG, "Esto es de prueba json" + jsonObject.toString())
        val jsonObjecUsuario = JSONObject()
        jsonObjecUsuario.put("nombre", "Robert")
        jsonObjecUsuario.put("correo", "robekodemia@outlook.com")
        jsonObjecUsuario.put("telefono", "321654987")
        Log.d(TAG, "Esto es de prueba json" + jsonObjecUsuario.toString())

        val cadenaDos = """
        {
            "nombre":"Robert",
            "telefono":321654987,
            "casado":false,
            "coutaHora":159.20,
            "diasSemanaTrabajo":[
                "lunes",
                "martes",
                "miercoles",
                "jueves"
            ],
            "observaciones":[
                {"fecha":"25/01/2021","comentario":"Todo bien"},
                {"fecha":"02/01/2021","comentario":"LLego tarde"}
            ]            
        }
    """.trimIndent()
        try {
            val empleado = Gson().fromJson(cadenaDos, Empleado::class.java)
            Log.d(TAG, empleado.nombre)
            Log.d(TAG, empleado.diasSemanaTrabajo.size.toString())
            for (dia in empleado.diasSemanaTrabajo) {
                Log.d(TAG, dia)
            }
//            Log.d(TAG,empleado.observaciones)
            for (observacion in empleado.observaciones) {
                Log.d(TAG, observacion.fecha)
                Log.d(TAG, observacion.comentario)
            }

        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }

        /*------------------------------------*/
        var cadenaTres = """
        [{
            "nombre":"Fabian",
            "telefono":123456879,
            "casado":true,
            "coutaHora":1100.20,
            "diasSemanaTrabajo":[
                "lunes",
                "martes",
                "miercoles",
                "jueves",
                "viernes"
            ],
            "observaciones":[
                {"fecha":"11/01/2021","comentario":"Juanetes"},
                {"fecha":"22/02/2021","comentario":"ALgo raro por aca"}
            ]            
        }]
    """.trimIndent()
        //cadenaTres ="["+cadenaDos+"]," + cadenaTres
        try {
            val empleados = Gson().fromJson(cadenaTres, Array<Empleado>::class.java)
            for (empleado in empleados) {
                Log.d(TAG, empleado.nombre)
                Log.d(TAG, empleado.telefono.toString())
                Log.d(TAG, empleado.casado.toString())
                Log.d(TAG, empleado.coutaHora.toString())
                for (dia in empleado.diasSemanaTrabajo) {
                    Log.d(TAG, dia)
                }
                for (observacion in empleado.observaciones) {
                    Log.d(TAG, observacion.fecha)
                    Log.d(TAG, observacion.comentario)
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }

        /*------------------------------------*/
        var cadenaCuatro = """
        {
            "nombre":"Jona",
            "telefono":99999,
            "casado":false,
            "couta_hora":222.20,
            "dias-De-Semana-Trabajo":[
                "lunes",
                "martes",
                "miercoles",
                "jueves",
                "viernes"
            ],
            "observaciones":[
                {"fecha":"11/01/2021","comentario":"Juanetes"},
                {"fecha":"22/02/2021","comentario":"ALgo raro por aca"}
            ]            
        }
    """.trimIndent()
        val empleadoSerialization = Json.decodeFromString<Empleado>(cadenaCuatro)
        Log.d(TAG,empleadoSerialization.nombre)
        Log.d(TAG,empleadoSerialization.coutaHora.toString())

        /*-----------------------------*/


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
            val peticion = JsonObjectRequest(Request.Method.POST,
                getString(R.string.url_servidor) + getString(R.string.api_login), json,
                { response ->
                    val jsonObject = JSONObject(response.toString())
                    iniciarSesion((applicationContext), jsonObject)
                    if (validarSesion(applicationContext)) {
                        lanzarActivity()
                    }
                    Log.d(TAG, response.toString())
                },
                { error ->
                    Log.e(TAG, error.toString())
                })
            cola.add(peticion)
        }
    }

    fun lanzarActivity() {
        val intent = Intent(this, MainActivity::class.java)
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Para dejar solo la acivity que estoy llamando
        startActivity(intent)
        finish()
    }

}