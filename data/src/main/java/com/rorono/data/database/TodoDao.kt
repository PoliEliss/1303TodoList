package com.rorono.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rorono.data.entities.TodoEntity
import com.rorono.domine.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoEntity")
     fun getAll():List<TodoEntity>

    @Insert
    fun save(todo: TodoEntity)

    @Query("SELECT * FROM TodoEntity WHERE id=(:id)")
     fun getById(id: Long):TodoEntity

     @Delete
     fun delete(todo: TodoEntity)



}