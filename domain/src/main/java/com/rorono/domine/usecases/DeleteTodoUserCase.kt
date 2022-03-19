package com.rorono.domine.usecases

import com.rorono.domine.implementation.Database
import com.rorono.domine.model.Todo

class DeleteTodoUserCase(private val database: Database) {

    suspend fun deleteTodo(todo: Todo) = database.deleteTodo(todo)
}