package com.marcos.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel(application: Application) : AndroidViewModel(application) {

    // Etiqueta del log
    val TAG_LOG: String = "ViewModel"

    // Lista de la secuencia. Es mutable para que sea editable
    val rondaArray = mutableListOf<Int>()


    // definimos una MutableLiveData para poder observar los valores de la MutableList<Int>
    val livedata_ronda = MutableLiveData<MutableList<Int>>()

    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        //livedata_ronda.value = rondaArray
    }

    //funcion de añadir el numero random de la secuencia a nuestro array
    fun añadirRonda(ronda: Int) {
        // añadimos entero random a la lista
        rondaArray.add(ronda)
        // actualizamos el livedata, de esta manera si hay un observador este recibirá la nueva lista
        livedata_ronda.value = rondaArray
        //mostramos la secuencia generada, que deberia ser identica a la de la app, en el Logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata: $rondaArray")
    }


    val context = getApplication<Application>().applicationContext


    val db = Room
        .databaseBuilder(
            context,
            Database::class.java, "simon-dice"
        ).allowMainThreadQueries().build()

    fun insertDB(rondaMax:Int) {
        var insertCorrutine: Job? = null
        insertCorrutine = GlobalScope.launch(Dispatchers.Main) {
            try {

                val dao = db.datosDao()

                val fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))
                val array: ArrayList<Datos> = arrayListOf(Datos(rondaMax, fecha))
                dao.insertDatos(array)

            } catch (ex: NullPointerException) {
                Log.d(TAG_LOG, ex.toString())
            }
        }
    }

    fun selectDB():Int{
        var record = 0
        var value = 0;
        var selectCorrutine: Job? = null
        selectCorrutine = GlobalScope.launch(Dispatchers.Main) {
            try {

                val dao = db.datosDao()

                record = dao.getMaxRound()
                Log.d(TAG_LOG, "Record durante el metodo: $record")
                value = record

            } catch (ex: NullPointerException) {
                Log.d(TAG_LOG, ex.toString())
            }
            Log.d(TAG_LOG, "Record fuera del trycatch: $record")
        }
        Log.d(TAG_LOG, "Record fuera de la corrutina: $record")
        return record
    }

}