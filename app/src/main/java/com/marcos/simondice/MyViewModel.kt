package com.marcos.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel : ViewModel() {

    // Etiqueta del log
    val TAG_LOG: String = "ViewModel"

    // Lista de la secuencia. Es mutable para que sea editable
    val secuencia = mutableListOf<Int>()


    // definimos una MutableLiveData para poder observar los valores de la MutableList<Int>
    val livedata_secuencia = MutableLiveData<MutableList<Int>>()

    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        livedata_secuencia.value = secuencia
    }

    //funcion de añadir el numero random de la secuencia a nuestro array
    fun añadirRandom(numeroRandom:Int) {
        // añadimos entero random a la lista
        secuencia.add(numeroRandom)
        // actualizamos el livedata, de esta manera si hay un observador este recibirá la nueva lista
        livedata_secuencia.value = secuencia
        //mostramos la secuencia generada, que deberia ser identica a la de la app, en el Logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata: $secuencia")
    }
}