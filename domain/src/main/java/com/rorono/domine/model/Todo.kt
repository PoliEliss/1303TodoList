package com.rorono.domine.model


import java.util.*

data class Todo(
    var title: String,
    var description: String = "",
    var isComplete: Boolean = false,
    val id: Long = (Math.random() * 100).toLong()
)


