package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(val toDoList: ArrayList<String>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val toDoTextView: TextView = itemView.findViewById(R.id.to_do_text)

        fun bind(text: String) {
            toDoTextView.text = text
        }
    }

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
        holder.bind(toDoList[position])
    }
}
