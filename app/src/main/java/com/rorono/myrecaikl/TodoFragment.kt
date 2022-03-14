package com.rorono.myrecaikl

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rorono.myrecaikl.StateToDo.*
import com.rorono.myrecaikl.databinding.FragmentTodoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val ARG_ID_TODO = "id_todo"

class TodoFragment : Fragment() {
    private lateinit var id: String
    private var toDo: ToDo = ToDo()
    private lateinit var binding: FragmentTodoBinding
    private lateinit var stateToDo: StateToDo
    private val repository: Repository = Repository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString(ARG_ID_TODO) ?: ""

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(layoutInflater)

        changeState(if (id.isEmpty()) EDIT else {
            CoroutineScope(Dispatchers.IO).launch {
                toDo = repository.getById(id)
                requireActivity().runOnUiThread {
                    binding.tvTitle.text = toDo.title
                    binding.tvDescription.text = toDo.description
                }
            }
            PREVIEW
        })
        return binding.root
    }

    fun saveTodo() {
        repository.save(
            ToDo(
                binding.etTitle.text.toString(),
                binding.etDescription.text.toString(),
                toDo.isComplete,
                toDo.id
            )
        )
    }

    private fun changeState(state: StateToDo) {
        stateToDo = state
        when (stateToDo) {
            PREVIEW -> {
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvDescription.visibility = View.VISIBLE
                binding.etTitle.visibility = View.GONE
                binding.etDescription.visibility = View.GONE
            }
            EDIT -> {
                binding.tvTitle.visibility = View.GONE
                binding.tvDescription.visibility = View.GONE
                binding.etTitle.visibility = View.VISIBLE
                binding.etDescription.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance(todoId: String) = TodoFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_ID_TODO, todoId)
            }
        }
    }
}

private enum class StateToDo() {
    PREVIEW, EDIT
}