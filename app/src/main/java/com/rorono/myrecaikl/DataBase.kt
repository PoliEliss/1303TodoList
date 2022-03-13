package com.rorono.myrecaikl

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1)
 abstract class DataBase : RoomDatabase() {
    abstract fun daoTodo(): DaoTodo
}