package com.marcos.simondice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var secuencia: ArrayList<Int>
    var ronda: Int = 0
    var record: Int = 0
    var contador: Int = 0

    lateinit var buttonStart: ImageButton

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
            if (compruebaColor(1, ronda)) {
                contador++
                generarSecuencia()
            } else {
                Log.d("JUEGO", "PARTIDA: GAME OVER")
                val rondaText: TextView = findViewById(R.id.textRound)
                rondaText.text = "GAME OVER!!!"

            }
        }
        buttonG.setOnClickListener {
            if (compruebaColor(2, ronda)) {
                contador++
                generarSecuencia()
            } else {
                Log.d("JUEGO", "PARTIDA: GAME OVER")
                val rondaText: TextView = findViewById(R.id.textRound)
                rondaText.text = "GAME OVER!!!"

            }
        }
        buttonB.setOnClickListener {
            if (compruebaColor(3, ronda)) {
                contador++
                generarSecuencia()
            } else {
                Log.d("JUEGO", "PARTIDA: GAME OVER")
                val rondaText: TextView = findViewById(R.id.textRound)
                rondaText.text = "GAME OVER!!!"

            }
        }
        buttonR.setOnClickListener {
            if (compruebaColor(4, ronda)) {
                contador++
                generarSecuencia()
            } else {
                Log.d("JUEGO", "PARTIDA: GAME OVER")
                val rondaText: TextView = findViewById(R.id.textRound)
                rondaText.text = "GAME OVER!!!"

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
        ronda++
    }

    private fun generarSecuencia() {
        Log.d("JUEGO", "Genero secuencia y muestro " + ronda.toString())
        var i = 0
        while (i < ronda) {
            Log.d("JUEGO", "SECUENCIA: Comienza la secuencia")
            mostrarSecuencia()
            i++
            Log.d("JUEGO", "SECUENCIA: Termina la secuencia")
        }
    }

    private fun mostrarSecuencia() {

        var corutina: Job? = null
        corutina = GlobalScope.launch(Dispatchers.Main) {

            Log.d("JUEGO", "Coroutina" + corutina.toString())

            val numeroRandom = Random().nextInt(4) + 1

            Log.d("JUEGO", "COLOR: Nuevo color random generado " + numeroRandom.toString())
            secuencia.add(numeroRandom)
            val ButtonY: ImageButton = findViewById(R.id.buttonY)
            val ButtonG: ImageButton = findViewById(R.id.buttonG)
            val ButtonB: ImageButton = findViewById(R.id.buttonB)
            val ButtonR: ImageButton = findViewById(R.id.buttonR)

            Log.d("JUEGO", "Generado: " + numeroRandom.toString())

            delay(1000L)

            when (numeroRandom) {
                1 -> ButtonY.setImageResource(R.mipmap.yellowlight_button_foreground)
                2 -> ButtonG.setImageResource(R.mipmap.greenlight_button_foreground)
                3 -> ButtonB.setImageResource(R.mipmap.bluelight_button_foreground)
                else -> {
                    ButtonR.setImageResource(R.mipmap.redlight_button_foreground)
                }
            }

            delay(1000L)

            ButtonY.setImageResource(R.mipmap.yellow_button_foreground)
            ButtonG.setImageResource(R.mipmap.green_button_foreground)
            ButtonB.setImageResource(R.mipmap.blue_button_foreground)
            ButtonR.setImageResource(R.mipmap.red_button_foreground)

            Log.d("JUEGO", "COLOR: Nuevo color visualizado al jugador")

            secuencia.add(numeroRandom)
            Log.d("JUEGO", "SECUENCIA: ${secuencia.toString()}")
        }

    }

    private fun aguardaPosicion() {

    }

    private fun compruebaColor(color: Int, ronda: Int): Boolean {
        /*return if (contador == ronda) {
            color == secuencia.indexOf(ronda)
        } else {
            false
        }*/
        return true
    }

    private fun subirNivel() {

    }
}