package com.rorono.myrecaikl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {
    var todoList = ArrayList<ToDo>()

    fun appdateList(list:ArrayList<ToDo>) {
        todoList = list
        notifyDataSetChanged()
    }

    class TodoHolder(item: View) : RecyclerView.ViewHolder(item) {
        val checkBox:CheckBox = itemView.findViewById(R.id.checkbox)
        val title:TextView = itemView.findViewById(R.id.tvtitle)
        val description:TextView = itemView.findViewById(R.id.description)
        val buttonArray:ImageView =itemView.findViewById(R.id.arrov_hide)

        val editText:EditText = itemView.findViewById(R.id.edit_title)
        val linerLayout:LinearLayout = itemView.findViewById(R.id.liner_layout_edit)
        val saveText:TextView = itemView.findViewById(R.id.save_text)


        fun bind(toDo: ToDo) {
            title.setOnClickListener {
                shovEditText()
                editText.setText(toDo.title)
            }

            saveText.setOnClickListener {
                shovEditText()
                toDo.title = editText.text.toString()
                title.setText(toDo.title )
            }
            buttonArray.setOnClickListener {
                toDo.isShov = !toDo.isShov
                showDiscription(toDo.isShov)
            }


            showDiscription(toDo.isShov)
            checkBox.isChecked = toDo.isCompleit
            title.text = toDo.title
            description.text = toDo.desckription

        }
        fun showDiscription(boolean: Boolean){
            if (boolean) {

                description.visibility = View.VISIBLE
                buttonArray.setImageResource(R.drawable.ic_arrow_shov)
            } else {

                description.visibility = View.GONE
                buttonArray.setImageResource(R.drawable.ic_arrov_hide)
            }
        }

        fun shovEditText(){
            if(linerLayout.visibility == View.GONE) {
                linerLayout.visibility = View.VISIBLE
                title.visibility = View.GONE
            } else{
                linerLayout.visibility = View.GONE
                title.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoHolder(view)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun addTodo(toDo: ToDo){
        todoList.add(toDo)
        notifyDataSetChanged() // говорит о том, что данные внутри изменились
            // может здесь использовать plain text
    }

}