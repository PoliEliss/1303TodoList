package com.rorono.myrecaikl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var button2: Button
    lateinit var  repositor:Repositor

    private val adapter = TodoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repositor = Repositor(this)
        recyclerView = findViewById(R.id.rcView)
        button2 = findViewById(R.id.button_todo)
        init()

    }

    private fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter // это наш адаптер
        button2.setOnClickListener {
            val todo = ToDo("Test", "kkkkk")

            repositor.insert(todo)


            adapter.addTodo(todo)
            Thread{
               val getAll =  repositor.getAll()
                runOnUiThread{adapter.appdateList(getAll)}
            }

        }


    }


}

