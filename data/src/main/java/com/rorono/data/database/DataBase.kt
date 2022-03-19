package com.rorono.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rorono.data.entities.TodoEntity

@Database(entities = [TodoEntity::class], version = 3)
 abstract class DataBase : RoomDatabase() {
    abstract fun daoTodo(): TodoDao
}