package com.marcos.simondice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Datos(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "ronda") val ronda: Int?,
    @ColumnInfo(name = "fecha") val fecha: String?
)
