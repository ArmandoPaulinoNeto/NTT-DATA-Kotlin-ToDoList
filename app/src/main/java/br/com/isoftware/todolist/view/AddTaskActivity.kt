package br.com.isoftware.todolist.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import br.com.isoftware.todolist.databinding.ActiviteAddTaskBinding
import br.com.isoftware.todolist.extansions.format
import br.com.isoftware.todolist.extansions.text
import br.com.isoftware.todolist.model.Task
import br.com.isoftware.todolist.repository.TaskBank
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActiviteAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActiviteAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertListners()
    }

    private fun insertListners() {
        binding.textDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
            datePicker.addOnPositiveButtonClickListener {
                binding.textDate.text = Date(it).format()
            }
        }

        binding.textHour.editText?.setOnClickListener {
            val timePicker =
                MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
            timePicker.show(supportFragmentManager, "TIME_PICKER_TAG")
            timePicker.addOnPositiveButtonClickListener {
                val huor =
                    if (timePicker.hour in 0..9) "0${timePicker.hour}" else "${timePicker.hour}"
                val minute =
                    if (timePicker.minute in 0..9) "0${timePicker.hour}" else "${timePicker.minute}"
                binding.textHour.text = "$huor:$minute"
            }
        }

        binding.buttonCancelTask.setOnClickListener { finish() }

        binding.buttonSaveTask.setOnClickListener {
            if (binding.textTitle.text.isNotEmpty()
                && binding.textDate.text.isNotEmpty()
                && binding.textHour.text.isNotEmpty()
            ) {

                val task =
                    Task(binding.textTitle.text, binding.textDate.text, binding.textHour.text)
                TaskBank.insertTask(task)
                alert("Salvo!", ("Terefa agendada com sucesso!")).show()
                setResult(RESULT_OK)
                finish()
            } else {
                alert("Campos vazios!", ("Por favor preencha os campos corretamente.")).show()
            }
        }
    }
    private fun alert(title:String, message:String):AlertDialog.Builder{
        return AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Fechar", null)
    }
}