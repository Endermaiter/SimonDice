package com.marcos.simondice

import androidx.room.Database
import androidx.room.RoomDatabase

class DataBase {
    @Database(entities = [Datos::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun datosDao(): DatosDAO
    }
}