package br.com.isoftware.todolist.repository

import br.com.isoftware.todolist.model.Task

object TaskBank{

    private val dataBase = arrayListOf<Task>()

    fun insertTask(task: Task) {
        dataBase.add(task.copy(id = dataBase.size+1))
    }
    fun deleteTask(task: Task) {
        dataBase.remove(task)
    }
    fun selectAll() = dataBase

    fun selectId(id: Int) = dataBase.find{ it.id == id }
}