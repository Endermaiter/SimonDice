package com.marcos.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel : ViewModel() {

    // para que sea mas facil la etiqueta del log
    val TAG_LOG: String = "ViewModel"

    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    val secuencia = mutableListOf<Int>()

    // definimos una MutableLiveData
    // para poder observar los valores de la MutableList<Int>
    val livedata_secuencia = MutableLiveData<MutableList<Int>>()

    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        livedata_secuencia.value = secuencia
    }

    /**
     * añadimos entero random al
     */
    fun añadirRandom() {
        // añadimos entero random a la lista
        secuencia.add(Random.nextInt(1, 4))
        // actualizamos el livedata, de esta manera si hay un observador
        // este recibirá la nueva lista
        livedata_secuencia.value = secuencia
        // la mostramos en el logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata: $secuencia")
    }
}