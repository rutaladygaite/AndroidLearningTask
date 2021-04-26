package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private var toDoList: List<String>,
                    private var toDoListid: List<Int>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

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
        holder.bind(toDoList[position], toDoListid[position])
    }

    fun notifyDataChange(listOfData: List<String>, newListOfIds: List<Int>){
        // Notify data of RV that we updated something
        toDoList = listOfData
        toDoListid = newListOfIds
        notifyDataSetChanged()
    }

    // Describes an item view and its place within the RecyclerView
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val toDoTextView: TextView = itemView.findViewById(R.id.to_do_text)
        private val toDoTextId: TextView = itemView.findViewById(R.id.todo_id_text)

        fun bind(text: String, id: Int) {
            // Bind new text ids
            toDoTextView.text = text
            // Can format the text of id's here if we want to add the dot after the number or something
            toDoTextId.text = id.toString()
        }
    }
}
