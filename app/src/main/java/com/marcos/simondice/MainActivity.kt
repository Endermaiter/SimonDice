package com.marcos.simondice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

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
            contador++
            compruebaColor(1)
        }
        buttonG.setOnClickListener {
            contador++
            compruebaColor(2)
        }
        buttonB.setOnClickListener {
            contador++
            compruebaColor(3)
        }
        buttonR.setOnClickListener {
            contador++
            compruebaColor(4)
        }
    }


    private fun iniciarPartida() {
        Log.d("JUEGO", "PARTIDA: Comienza la partida")
        Log.d("JUEGO", "PARTIDA: Ronda = $ronda")
        ronda = 1
        val rondaText: TextView = findViewById(R.id.textRound)
        rondaText.text = "Round: $ronda"

        val recordText: TextView = findViewById(R.id.textRecord)
        recordText.text = "Record: $record"
        secuencia = arrayListOf()
    }


    private fun generarSecuencia() {
        Log.d("JUEGO", "Genero secuencia y muestro $ronda")
        var i = 0
        while (i < ronda) {

            Log.d("JUEGO", "SECUENCIA: Comienza la secuencia")

            val numeroRandom = Random().nextInt(4) + 1

            Log.d("JUEGO", "COLOR: Nuevo color random generado $numeroRandom")
            Log.d("JUEGO", "Generado: $numeroRandom")

            secuencia.add(numeroRandom)
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

                delay(1000L)

                when (secuencia[i]) {
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
                i++
            }
        }
        val toast = Toast.makeText(applicationContext, "Pulsa la secuencia", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun compruebaColor(color: Int){
        if(color==secuencia[contador]){
            ronda++
            generarSecuencia()
        }else{
            gameOver()
        }
    }

    private fun gameOver() {
        Log.d("JUEGO", "PARTIDA: GAME OVER")
        val toast1 = Toast.makeText(applicationContext, "GAME OVER!!!!!", Toast.LENGTH_SHORT)
        toast1.show()
        val toast2 = Toast.makeText(applicationContext, "PULSE DE NUEVO EL BOTON START", Toast.LENGTH_SHORT)
        toast2.show()
    }
}