package com.drackdesign.repasoprueba.com.example.repasoprueba

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cafes")
class Cafe (
    val nombre : String,
    @PrimaryKey(autoGenerate = true)
    var idCafe: Int
        )