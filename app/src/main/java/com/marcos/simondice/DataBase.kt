package com.marcos.simondice

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Datos::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun datosDao(): DatosDAO
}
