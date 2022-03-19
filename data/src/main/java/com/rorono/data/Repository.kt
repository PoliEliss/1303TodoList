package com.rorono.data

import android.content.Context
import androidx.room.Room
import com.rorono.data.entities.TodoEntity
import com.rorono.domine.implementation.Database
import com.rorono.domine.model.Todo

class Repository private constructor(context: Context) : Database {

    private val database =
        Room.databaseBuilder(context, com.rorono.data.database.DataBase::class.java, "todo").build()
    private val todoDao = database.daoTodo()


    companion object {
        lateinit var instance: Repository
        fun init(context: Context) {
            instance = Repository(context)
        }
    }

    override suspend fun getAllTodo(): List<Todo> {
        return todoDao.getAll().map { Todo(it.title, it.description, it.isComplete, it.id) }
    }

    override suspend fun saveTodo(todo: Todo) {
        todoDao.save(todo = TodoEntity(todo.title, todo.description, todo.isComplete, todo.id))
    }

    override suspend fun getTodoById(id: Long): Todo {
        return Todo("")
    }

    override suspend fun deleteTodo(todo: Todo) {

    }
}