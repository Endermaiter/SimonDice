package com.marcos.simondice

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import java.util.*

class MainActivity : AppCompatActivity() {

    var secuencia: ArrayList<Int> = arrayListOf()
    var ronda: Int = 0
    var record: Int = 0

    lateinit var buttonStart: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart = findViewById(R.id.start_button)
        buttonStart.setOnClickListener {
            iniciarPartida()
            generarSecuencia()
        }

    }

    private fun iniciarPartida() {
        Log.d("JUEGO", "PARTIDA: Comienza la partida")
        Log.d("JUEGO", "PARTIDA: Ronda = $ronda")
        // TODO inicializar array de secuencia
        ronda++
    }

    private fun generarSecuencia(){
        Log.d("JUEGO","Genero secuencia y muestro " + ronda.toString())
        var i = 0
        while (i < ronda) {
            Log.d("JUEGO", "SECUENCIA: Comienza la secuencia")
            mostrarSecuencia()
            i++
            Log.d("JUEGO", "SECUENCIA: Termina la secuencia")
        }
    }

    private fun mostrarSecuencia() {
        val numeroRandom = Random().nextInt(4) + 1

        Log.d("JUEGO", "COLOR: Nuevo color random generado " + numeroRandom.toString())
        secuencia.add(numeroRandom)
        /*val ButtonY: ImageButton = findViewById(R.id.buttonY)
        val ButtonG: ImageButton = findViewById(R.id.buttonG)
        val ButtonB: ImageButton = findViewById(R.id.buttonB)
        val ButtonR: ImageButton = findViewById(R.id.buttonR)*/

        Log.d("JUEGO", "Generado: " + numeroRandom.toString() )

        /*when (numeroRandom) {
            1 -> ButtonY.setImageResource(R.mipmap.yellowlight_button_foreground)
            2 -> ButtonG.setImageResource(R.mipmap.greenlight_button_foreground)
            3 -> ButtonB.setImageResource(R.mipmap.bluelight_button_foreground)
            else -> {
                ButtonR.setImageResource(R.mipmap.redlight_button_foreground)
            }
        }*/


       /* ButtonY.setImageResource(R.mipmap.yellow_button_foreground)
        ButtonG.setImageResource(R.mipmap.green_button_foreground)
        ButtonB.setImageResource(R.mipmap.blue_button_foreground)
        ButtonR.setImageResource(R.mipmap.red_button_foreground)*/

        Log.d("JUEGO", "COLOR: Nuevo color visualizado al jugador")

    }

    private fun aguardaPosicion() {

    }

    private fun compruebaColor() {

    }

    private fun subirNivel() {

    }
}