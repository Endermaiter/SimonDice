package com.marcos.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

    //SQLite

    val context = getApplication<Application>().applicationContext


    val db = Room
        .databaseBuilder(
            context,
            Database::class.java, "simon-dice"
        ).allowMainThreadQueries().build()

    //Firebase

    lateinit var database: DatabaseReference
    var fecha = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))

    fun insertDB(rondaMax: Int) {

        //SQLite

        var insertCorrutine: Job? = null
        insertCorrutine = GlobalScope.launch(Dispatchers.Main) {
            try {

                val dao = db.datosDao()

                val array: ArrayList<Datos> = arrayListOf(Datos(rondaMax, fecha))
                Log.d(TAG_LOG, "Datos para insertar(SQLiteDB): $array")
                dao.insertDatos(array)

            } catch (ex: NullPointerException) {
                Log.d(TAG_LOG, ex.toString())
            }
        }

        //FireBase (Insert into Real Time Data Base)

        database = Firebase.database.reference
        val rowRealTimeDB = Datos(rondaMax, fecha)
        Log.d(TAG_LOG, "Datos para insertar (RTDB): $rowRealTimeDB")
        database.child("SimonDice").child("Datos").setValue(rowRealTimeDB)
    }

    var record = 0
    val recordArray = mutableListOf<Int>()
    val livedata_record = MutableLiveData<Int>()


    fun getRecord() {
        var selectCorrutine: Job? = null
        selectCorrutine = GlobalScope.launch(Dispatchers.Main) {
            try {
                recordArray.clear()
                val dao = db.datosDao()

                record = dao.getMaxRound()
                recordArray.add(record)
                livedata_record.value = recordArray[0]

                Log.d(TAG_LOG, "Record en método getRecord(): $record")

            } catch (ex: NullPointerException) {
                Log.d(TAG_LOG, ex.toString())
            }
        }
    }

}