package com.example.todokpb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todokpb.R
import com.example.todokpb.databinding.TodoItemLayoutBinding
import com.example.todokpb.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoItemLayoutInterface {
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false)
        return TodoViewHolder(view)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        holder.view.todo = todoList[position]
        holder.view.checklistener = this
        holder.view.editlistener = this
        holder.view.checkTask.isChecked = false

//        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
//
//        checktask.text = todoList[position].title
//        checktask.isChecked = false
//
//        checktask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked){
//                adapterOnClick(todoList[position])
//            }
//        }
//
//        imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        checktask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked == true) {
//                adapterOnClick(todoList[position])
//            }
//        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}