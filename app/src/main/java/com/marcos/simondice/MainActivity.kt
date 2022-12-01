package com.marcos.simondice


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import java.util.*
import kotlinx.coroutines.*
import kotlin.collections.*
import androidx.lifecycle.Observer
import androidx.room.Room
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var secuencia: ArrayList<Int>
    var ronda: Int = 0
    var record: Int = 0
    var contador: Int = 0

    lateinit var buttonStart: ImageButton

    //viewModel

    val miModelo by viewModels<MyViewModel>()

    //SQLite

    val db = Room.databaseBuilder(
        applicationContext,
        DataBase.AppDatabase::class.java, "simon-dice"
    ).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: ImageButton = findViewById(R.id.start_button)
        val buttonY: ImageButton = findViewById(R.id.buttonY)
        val buttonG: ImageButton = findViewById(R.id.buttonG)
        val buttonR: ImageButton = findViewById(R.id.buttonR)
        val buttonB: ImageButton = findViewById(R.id.buttonB)

        buttonStart.setOnClickListener {
            iniciarPartida()
            generarSecuencia()
        }

        buttonY.setOnClickListener {
            contador++
            if (compruebaColor(1)) {
                if (contador == secuencia.size) {
                    generarSecuencia()
                }
            } else {
                gameOver()
            }
        }
        buttonG.setOnClickListener {
            contador++
            if (compruebaColor(2)) {
                if (contador == secuencia.size) {
                    generarSecuencia()
                }
            } else {
                gameOver()
            }
        }
        buttonB.setOnClickListener {
            contador++
            if (compruebaColor(3)) {
                if (contador == secuencia.size) {
                    generarSecuencia()
                }
            } else {
                gameOver()
            }
        }
        buttonR.setOnClickListener {
            contador++
            if (compruebaColor(4)) {
                if (contador == secuencia.size) {
                    generarSecuencia()
                }
            } else {
                gameOver()
            }
        }
    }


    private fun iniciarPartida() {
        Log.d("JUEGO", "PARTIDA: Comienza la partida")
        Log.d("JUEGO", "PARTIDA: Ronda = $ronda")
        val rondaText: TextView = findViewById(R.id.textRound)
        rondaText.text = "Round: $ronda"

        val recordText: TextView = findViewById(R.id.textRecord)
        recordText.text = "Record: $record"
        secuencia = arrayListOf()
    }


    private fun generarSecuencia() {
        contador = 0
        ronda = 1
        Log.d("JUEGO", "Genero secuencia y muestro $ronda")
        var i = 0

        while (i < ronda) {

            Log.d("JUEGO", "SECUENCIA: Comienza la secuencia")

            val numeroRandom = Random().nextInt(4) + 1

            Log.d("JUEGO", "COLOR: Nuevo color random generado $numeroRandom")
            Log.d("JUEGO", "Generado: $numeroRandom")

            secuencia.add(numeroRandom)
            //
            mostrarColor(secuencia)
            i++
            Log.d("JUEGO", "SECUENCIA:Secuencia $secuencia")
            Log.d("JUEGO", "SECUENCIA: Termina la secuencia")
        }
    }

    private fun mostrarColor(secuencia: ArrayList<Int>) {

        var i = 0

        var corutina: Job? = null
        corutina = GlobalScope.launch(Dispatchers.Main) {

            while (i < secuencia.size) {

                Log.d("JUEGO", "Coroutina" + corutina.toString())

                val ButtonY: ImageButton = findViewById(R.id.buttonY)
                val ButtonG: ImageButton = findViewById(R.id.buttonG)
                val ButtonB: ImageButton = findViewById(R.id.buttonB)
                val ButtonR: ImageButton = findViewById(R.id.buttonR)

                delay(500L)

                when (secuencia[i]) {
                    1 -> ButtonY.setImageResource(R.mipmap.yellowlight_button_foreground)
                    2 -> ButtonG.setImageResource(R.mipmap.greenlight_button_foreground)
                    3 -> ButtonB.setImageResource(R.mipmap.bluelight_button_foreground)
                    else -> {
                        ButtonR.setImageResource(R.mipmap.redlight_button_foreground)
                    }
                }

                delay(500L)

                ButtonY.setImageResource(R.mipmap.yellow_button_foreground)
                ButtonG.setImageResource(R.mipmap.green_button_foreground)
                ButtonB.setImageResource(R.mipmap.blue_button_foreground)
                ButtonR.setImageResource(R.mipmap.red_button_foreground)

                Log.d("JUEGO", "COLOR: Nuevo color visualizado al jugador")
                i++
            }
        }
        val toast = Toast.makeText(applicationContext, "Pulsa la secuencia", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun compruebaColor(color: Int): Boolean {

        if (contador == secuencia.size){
            val rondaSum = ronda+1
            val rondaText: TextView = findViewById(R.id.textRound)
            rondaText.text = "Round: $rondaSum"
        }

        return if (color == secuencia[contador - 1]) {
            ronda++
            //ESQUEMA MVVC

            //añadimos el numero random generado en la secuencia a la lista del ViewModel
            miModelo.añadirRonda(ronda)
            //instanciamos el observador
            miModelo.livedata_ronda.observe(
                this, Observer(
                    // funcion que llamaremos cada vez que cambie el valor del observable
                    fun(nuevaListaRandom: MutableList<Int>) {
                        // actualizamos LOGd en caso de recibir datos
                        Log.d(miModelo.TAG_LOG, nuevaListaRandom.toString())
                    }
                )
            )
            true
        } else {
            false
        }
    }

    private fun gameOver() {
        Log.d("JUEGO", "PARTIDA: GAME OVER")
        val toast1 = Toast.makeText(
            applicationContext, "GAME OVER!!, PULSE DE NUEVO EL BOTON START", Toast.LENGTH_SHORT
        )
        toast1.show()

        val rondaText: TextView = findViewById(R.id.textRound)
        rondaText.text = "Round: 0"

        val numMaxArray: Int = miModelo.rondaArray.size
        val rondaMax: Int = miModelo.rondaArray[numMaxArray-1]
        Log.d(miModelo.TAG_LOG,"Ronda máxima alcanzada: $rondaMax")

        //SQLite

        val dao = db.datosDao()
        val fecha = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))
        dao.insertDatos(1, rondaMax, fecha)

        miModelo.rondaArray.clear()
    }
}