package com.unhas.ac.id.roomdb.crud.myapps.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var id : Int,

    var title: String,

    var description: String
)