package com.marcos.simondice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import java.util.*

class MainActivity : AppCompatActivity() {

    var secuencia: ArrayList<String> = TODO()
    val ronda: Int = secuencia.size
    val record:Int
    val colorSeleccionado:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ButtonStart: Button = findViewById(R.id.buttonStart)
        ButtonStart.setOnClickListener{
            iniciarPartida()
        }

    }

    private fun iniciarPartida(){
        Log.d("JUEGO", "PARTIDA: Comienza la partida")
        Log.d("JUEGO","PARTIDA: Ronda = $ronda")
        Log.d("JUEGO", "SECUENCIA: Comienza la secuencia")
        Log.d("JUEGO", "SECUENCIA: Termina la secuencia")

        Log.d("JUEGO", "PARTIDA: Esperando al usuario")
        Log.d("JUEGO", "PARTIDA: El jugador ha pulsado la secuencia de colores")

        Log.d("JUEGO", "SECUENCIA: Comprobando secuencia de colores correcta...")
        Log.d("JUEGO","SECUENCIA: Color incorrecto --> GAME OVER")
        Log.d("JUEGO","SECUENCIA: Color correcto. Saltando al siguiente...")

        Log.d("JUEGO","PARTIDA: Todos los colores son correctos. Pasando al siguiente nivel...")

    }

    private fun colorRandom() {
        val numeroRandom = Random().nextInt(4)+1

        Log.d("JUEGO", "COLOR: Nuevo color random generado")

        val ButtonY: ImageButton = findViewById(R.id.buttonY)
        val ButtonG: ImageButton = findViewById(R.id.buttonG)
        val ButtonB: ImageButton = findViewById(R.id.buttonB)
        val ButtonR: ImageButton = findViewById(R.id.buttonR)

        val buttonColor = when (numeroRandom) {
            1 -> ButtonY.setImageResource(R.mipmap.yellowLight_button_foreground)
            2 -> ButtonG.setImageResource(R.mipmap.greenLight_button_foreground)
            3 -> ButtonB.setImageResource(R.mipmap.blueLight_button_foreground)
            else -> {ButtonR.setImageResource(R.mipmap.redLight_button_foreground)}
        }

        Log.d("JUEGO", "COLOR: Nuevo color visualizado al jugador")

    }
}