package com.marcos.simondice

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

        val ButtonY: Button = findViewById(R.id.buttonY)
        val ButtonG: Button = findViewById(R.id.buttonG)
        val ButtonB: Button = findViewById(R.id.buttonB)
        val ButtonR: Button = findViewById(R.id.buttonR)

        val buttonColor = when (numeroRandom) {
            1 -> ButtonY.setBackgroundColor(Color.YELLOW)
            2 -> ButtonG.setBackgroundColor(Color.GREEN)
            3 -> ButtonB.setBackgroundColor(Color.BLUE)
            else -> {ButtonR.setBackgroundColor(Color.RED)}
        }

        Log.d("JUEGO", "COLOR: Nuevo color visualizado al jugador")

    }
}