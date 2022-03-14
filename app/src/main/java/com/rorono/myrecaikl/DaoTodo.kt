package com.rorono.myrecaikl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoTodo {
    @Query("SELECT * FROM ToDo")
     fun getAll():List<ToDo>

    @Insert
    fun save(toDo: ToDo)

    @Query("SELECT * FROM ToDo WHERE id=(:id)")
     fun getToDoById(id: String):ToDo
}