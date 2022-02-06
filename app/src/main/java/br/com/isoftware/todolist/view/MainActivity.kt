package br.com.isoftware.todolist.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.isoftware.todolist.databinding.ActivityMainBinding
import br.com.isoftware.todolist.repository.TaskBank

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter by lazy{TaskListAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewTask.adapter = adapter
        showList()

        insertListeners()
    }
    private fun insertListeners(){
        binding.buttonAddTask.setOnClickListener {
            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }
        adapter.listnerEdit ={
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }
        adapter.listnerDelete={
            TaskBank.deleteTask(it)
            showList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            showList()
        }
    }

    private fun showList(){
        val taskAll = TaskBank.selectAll()
        binding.includeEmpty.emptyViewState.visibility = if(taskAll.isEmpty()) View.VISIBLE else View.GONE
        adapter.submitList(taskAll)
    }

    companion object{
        private const val CREATE_NEW_TASK = 1000
    }
    private fun alert(title:String, message:String): AlertDialog.Builder{
        return AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Fechar", null)
    }
}