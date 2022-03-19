package com.rorono.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TodoEntity(
    var title: String = "",
    var description: String = "",
    var isComplete: Boolean = false,
    @PrimaryKey
    val id: Long = UUID.randomUUID().timestamp()
) {}


