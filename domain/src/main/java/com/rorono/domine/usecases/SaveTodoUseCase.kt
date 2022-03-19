package com.rorono.domine.usecases

import com.rorono.domine.implementation.Database
import com.rorono.domine.model.Todo

class SaveTodoUseCase(private val database: Database) {
    suspend fun saveTodo(todo: Todo) = database.saveTodo(todo)
}