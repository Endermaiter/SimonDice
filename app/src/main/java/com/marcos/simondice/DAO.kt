package com.marcos.simondice

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DatosDAO {
    @Query("SELECT * FROM Datos WHERE ronda = (SELECT max(ronda) FROM Datos)")
    fun getRowMaxRound(): List<Datos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatos(id: Int, ronda: Int, fecha: String)
}