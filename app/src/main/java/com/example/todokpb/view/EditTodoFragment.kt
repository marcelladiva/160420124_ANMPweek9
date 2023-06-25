package com.example.todokpb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todokpb.R
import com.example.todokpb.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        val btnCreateTodo = view.findViewById<Button>(R.id.btnAdd)
        val radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
        val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
        val txtNotes = view.findViewById<EditText>(R.id.txtNotes)

        txtJudulTodo.text = "Edit Todo"
        btnCreateTodo.text = "Save Changes"

        btnCreateTodo.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            val txtTitle = view?.findViewById<TextView>(R.id.txtTitle)
            val txtNotes = view?.findViewById<TextView>(R.id.txtNotes)
            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)

            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)

            when (it.priority) {
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                else -> radioHigh?.isChecked = true
            }
        })
    }
}