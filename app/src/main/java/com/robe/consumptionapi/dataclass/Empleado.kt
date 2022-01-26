package com.robe.consumptionapi.dataclass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//data class Empleado(
//    val nombre: String,
//    val telefono: Int,
//    val casado: Boolean,
//    val coutaHora: Double,
//    val diasSemanaTrabajo: List<String>,
//    val observaciones: List<Observacion>
//)

@Serializable
data class Empleado(
    val nombre: String,
    val telefono: Int,
    val casado: Boolean,

    @SerialName("couta_hora")
    val coutaHora: Double,

    @SerialName("dias-De-Semana-Trabajo")
    val diasSemanaTrabajo: List<String>,
    val observaciones: List<Observacion>
)
