package com.kusitms.ovengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.StorageData
import com.kusitms.ovengers.data.TestResponse

class StorageAdapter (
    private val storageList: MutableList<StorageData> = mutableListOf(),
    private val clickListener:(StorageData)->Unit
) : RecyclerView.Adapter<ViewHolder>(){

    fun updateList(newList: List<StorageData>) {
        storageList.clear()
        storageList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_storage,parent,false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storageList = storageList[position]
        holder.bind(storageList, clickListener)
    }

    override fun getItemCount(): Int {
        return storageList.size
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val name : TextView = view.findViewById<TextView>(R.id.name)
    private val button : ImageButton = view.findViewById(R.id.button)

    fun bind(task: StorageData, clickListener: (StorageData) -> Unit) {
        name.text = task.name

        view.setOnClickListener {
            clickListener(task)
        }
    }
}