package com.rorono.myrecaikl

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rorono.myrecaikl.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository: Repository = Repository.instance
    private var todoList = listOf<ToDo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.recyclerTodo.let {
            it.layoutManager = LinearLayoutManager(this@MainActivity)
            it.adapter = TodoAdapter()
        }
        binding.fabAddTodo.setOnClickListener { openTodoFragment("") }
        updateTodoList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateTodoList(){
        CoroutineScope(Dispatchers.IO).launch {
            todoList = repository.getAll()
            this@MainActivity.runOnUiThread{ binding.recyclerTodo.adapter?.notifyDataSetChanged() }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size != 0) {
            supportFragmentManager.fragments.forEach {
                if(it is TodoFragment) {
                    CoroutineScope(Dispatchers.IO).launch {
                        it.saveTodo()
                        todoList = repository.getAll()
                        this@MainActivity.runOnUiThread{
                            supportFragmentManager.beginTransaction().remove(it).commit()
                            binding.recyclerTodo.adapter?.notifyDataSetChanged()
                        }
                    }
                    return
                }
            }
        }
        super.onBackPressed()
    }

    private fun openTodoFragment(id: String) {
        supportFragmentManager.beginTransaction().add(R.id.container, TodoFragment.newInstance(id)).commit()
    }


    inner class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

        inner class TodoHolder(item: View) : RecyclerView.ViewHolder(item) {
            private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
            private val title: TextView = itemView.findViewById(R.id.tvtitle)
            private val buttonDelete: ImageView = itemView.findViewById(R.id.iv_delete)

            fun bind(toDo: ToDo) {
                title.setOnClickListener { openTodoFragment(toDo.id) }
                buttonDelete.setOnClickListener { }
                checkBox.setOnClickListener { }
                title.text = toDo.title
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
            return TodoHolder(view)
        }

        override fun onBindViewHolder(holder: TodoHolder, position: Int) {
            holder.bind(todoList[position])
        }

        override fun getItemCount(): Int {
            return todoList.size
        }
    }
}

