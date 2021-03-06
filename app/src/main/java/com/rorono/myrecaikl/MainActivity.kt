package com.rorono.myrecaikl

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rorono.data.Repository
import com.rorono.domine.model.Todo
import com.rorono.domine.usecases.GetAllTodoUseCase
import com.rorono.myrecaikl.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val getAllTodoUseCase = GetAllTodoUseCase(Repository.instance)
    private var todoList = listOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_options,menu)
        return true
    }

    private fun init() {
        binding.recyclerTodo.let {
            it.layoutManager = LinearLayoutManager(this@MainActivity)
            it.adapter = TodoAdapter()
        }
        binding.fabAddTodo.setOnClickListener { openTodoFragment(0) }
        updateTodoList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateTodoList() {
        CoroutineScope(Dispatchers.IO).launch {
            todoList = getAllTodoUseCase.getAllTodo()
                          this@MainActivity.runOnUiThread { binding.recyclerTodo.adapter?.notifyDataSetChanged() }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size != 0) {
            supportFragmentManager.fragments.forEach {
                if (it is TodoFragment) {
                    CoroutineScope(Dispatchers.IO).launch {
                        it.saveTodo()
                        todoList = getAllTodoUseCase.getAllTodo()
                        this@MainActivity.runOnUiThread {
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

    private fun openTodoFragment(id: Long) {
        supportFragmentManager.beginTransaction().add(R.id.container, TodoFragment.newInstance(id))
            .commit()
    }


    inner class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {


        inner class TodoHolder(item: View) : RecyclerView.ViewHolder(item) {

            private val title: TextView = itemView.findViewById(R.id.tv_title)
            private val description:TextView = itemView.findViewById(R.id.tv_description)
            private val imageOptions:ImageView = itemView.findViewById(R.id.menu_option)

            // ???????????????? desc

            // ???????????? ??????????


            fun bind(todo: Todo) {
                title.setOnClickListener { openTodoFragment(todo.id) }
               /* buttonDelete.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        repository.deleteToDo(toDo)
                        todoList = todoList.filter {
                            it.id != toDo.id
                        }
                        runOnUiThread{
                            this@TodoAdapter.notifyDataSetChanged()
                        }
                    }


                }
*/
                title.text = todo.title
                description.text = todo.title
                imageOptions.setOnClickListener {
                    val popup = PopupMenu(this@MainActivity,imageOptions)
                    val inflater:MenuInflater = popup.menuInflater
                    inflater.inflate(R.menu.menu_options,popup.menu)
                    popup.show()
                }

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

