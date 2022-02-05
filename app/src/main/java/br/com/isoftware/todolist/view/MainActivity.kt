package br.com.isoftware.todolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.isoftware.todolist.databinding.ActivityMainBinding
import br.com.isoftware.todolist.repository.TaskBank

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter by lazy{TaskListAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }
    private fun insertListeners(){
        binding.buttonAddTask.setOnClickListener {

            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CREATE_NEW_TASK){
            binding.recyclerViewTask.adapter = adapter
            adapter.submitList(TaskBank.selectAll())
        }
    }
    companion object{
        private const val CREATE_NEW_TASK = 1000
    }
}