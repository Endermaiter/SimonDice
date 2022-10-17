package com.marcos.simondice

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun colorRandom() {
        val numeroRandom = Random().nextInt(4)+1

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



    }
}