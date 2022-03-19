package com.rorono.domine.usecases

import com.rorono.domine.implementation.Database

class GetAllTodoUseCase(private val database: Database) {

    suspend fun getAllTodo() = database.getAllTodo()
}