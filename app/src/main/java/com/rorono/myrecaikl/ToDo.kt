package com.rorono.myrecaikl

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ToDo(
    var title: String = "",
    var description: String = "",
    var isComplete: Boolean = false,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {

}


