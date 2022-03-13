package com.rorono.myrecaikl

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class Repositor (context: Context){

    val dataBase = Room.databaseBuilder(
        context,
        DataBase::class.java, "todo"
    ).build()

    fun insert(toDo: ToDo){
        Thread {
            dataBase.daoTodo().save(toDo)
        }.run()
    }

    fun getAll():ArrayList<ToDo>{
     return   dataBase.daoTodo().getTodo()
    }

}