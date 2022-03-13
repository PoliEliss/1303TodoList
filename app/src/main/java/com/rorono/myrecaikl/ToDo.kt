package com.rorono.myrecaikl

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ToDo(
    var title: String,
    var desckription: String,
    var isCompleit: Boolean = false,
    var isShov: Boolean = false,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {

}


