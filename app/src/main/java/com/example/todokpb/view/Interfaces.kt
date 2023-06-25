package com.example.todokpb.view

import android.view.View
import android.widget.CompoundButton
import com.example.todokpb.model.Todo

interface TodoCreateLayoutInterface {
    fun onRadioClick(v:View, priority:Int, obj:Todo)
    fun onButtonAddClick(v: View)
}

interface TodoItemLayoutInterface {
    fun onCheckedChange(cb: CompoundButton,
                        isChecked:Boolean,
                        obj: Todo)

    fun onTodoEditClick(v: View)
}