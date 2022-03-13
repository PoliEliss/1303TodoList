package com.rorono.myrecaikl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoTodo {
    @Query("SELECT * FROM ToDo")
    fun getTodo():ArrayList<ToDo>

    @Insert
    fun save(toDo: ToDo)
}