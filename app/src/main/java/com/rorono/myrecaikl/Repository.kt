package com.rorono.myrecaikl

import android.content.Context
import androidx.room.Room

class Repository private constructor(context: Context) {

    private val database = Room.databaseBuilder(context, DataBase::class.java, "todo").build()
    private val daoToDo = database.daoTodo()


    fun save(toDo: ToDo): Unit = daoToDo.save(toDo)

    suspend fun getAll(): List<ToDo> = database.daoTodo().getAll()

    suspend fun getById(id: String): ToDo = daoToDo.getToDoById(id)

    companion object {
        lateinit var instance: Repository
        fun init(context: Context) {
            instance = Repository(context)
        }

    }
}