package com.rorono.domine.implementation

import com.rorono.domine.model.Todo

interface Database {
    suspend fun getAllTodo(): List<Todo>
    suspend fun saveTodo(todo: Todo)
    suspend fun getTodoById(id: Long): Todo
    suspend fun deleteTodo(todo: Todo)
}