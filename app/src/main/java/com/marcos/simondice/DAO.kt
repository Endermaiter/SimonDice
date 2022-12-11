package com.marcos.simondice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatosDAO {

    @Query("SELECT ronda FROM Datos WHERE ronda = (SELECT max(ronda) FROM Datos)")
    fun getMaxRound(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatos(fila:ArrayList<Datos>)
}