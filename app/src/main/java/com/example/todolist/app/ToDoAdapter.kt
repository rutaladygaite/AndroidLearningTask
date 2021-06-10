package com.example.todolist.app

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class ToDoAdapter(
    private var toDoList: List<String>,
    private var toDoListId: List<Int>,
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ToDoViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return toDoList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        //Think of how to pass list of numbers instead of position number, so it would be 1,2,3....
        holder.bind(toDoList[position], toDoListId[position])

        val context = holder.itemView.context
        val textSelected = toDoList[position]
        val idSelected = toDoListId[position]

        holder.itemView.setOnLongClickListener {
            context.startActivity(
                //Starting a new activity and adding extra values
                Intent(context, ChangeItemActivity::class.java)
                    .putExtra(ChangeItemActivity.TEXT_SELECTED, textSelected)
                    .putExtra(ChangeItemActivity.TEXT_ID_SELECTED, idSelected)
            )
            true
        }
    }

    fun notifyDataChange(listOfData: List<String>, newListOfIds: List<Int>) {
        // Notify data of RV that we updated something
        toDoList = listOfData
        toDoListId = newListOfIds
        notifyDataSetChanged()
    }

    // Describes an item view and its place within the RecyclerView
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val toDoTextView: TextView = itemView.findViewById(R.id.to_do_text)
        private val toDoTextId: TextView = itemView.findViewById(R.id.todo_id_text)

        @SuppressLint("SetTextI18n")
        fun bind(text: String, id: Int) {
            toDoTextView.textSize = 16F
            toDoTextId.textSize = 16F
            toDoTextView.text = text
            toDoTextId.text = "$id."
        }
    }
}
