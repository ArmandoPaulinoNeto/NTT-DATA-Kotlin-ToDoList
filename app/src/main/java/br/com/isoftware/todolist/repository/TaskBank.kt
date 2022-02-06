package br.com.isoftware.todolist.repository

import br.com.isoftware.todolist.model.Task

object TaskBank{

    private val dataBase = arrayListOf<Task>()

    fun insertTask(task: Task) {
        if(task.id == 0){
            dataBase.add(task.copy(id = dataBase.size+1))
        }else{
            dataBase.remove(task)
            dataBase.add(task)
        }
    }
    fun selectAll() = dataBase.toList()

    fun selectId(id: Int) = dataBase.find{ it.id == id }

    fun deleteTask(task: Task) {
        dataBase.remove(task)
    }
}