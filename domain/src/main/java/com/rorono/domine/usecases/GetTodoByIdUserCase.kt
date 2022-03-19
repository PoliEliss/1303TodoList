package com.rorono.domine.usecases

import com.rorono.domine.implementation.Database
import com.rorono.domine.model.Todo

class GetTodoByIdUserCase(private val database: Database) {

    suspend fun getTodoById(id: Long): Todo = database.getTodoById(id)
}