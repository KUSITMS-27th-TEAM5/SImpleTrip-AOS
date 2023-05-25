package com.kusitms.ovengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.AlarmData

class NotifyAdapter (
    private val alarm: MutableList<AlarmData> = mutableListOf(),
    private val clickListener:(AlarmData)->Unit
) : RecyclerView.Adapter<NotifyViewHolder>(){

    fun updateAlarm(newList: List<AlarmData>) {
        alarm.clear()
        alarm.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_nofity,parent,false)
        return NotifyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: NotifyViewHolder, position: Int) {
        val alarm = alarm[position]
        holder.bind(alarm, clickListener)
    }

    override fun getItemCount(): Int {
        return alarm.size
    }
}

class NotifyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val text : TextView = view.findViewById<TextView>(R.id.text)
    private val date : TextView = view.findViewById<TextView>(R.id.date)

    fun bind(task: AlarmData, clickListener: (AlarmData) -> Unit) {
        var message = task.message
        message = message.replace("₩n", "\n")

        text.text = message.toString()
        date.text = task.createdDate.toString()


        view.setOnClickListener {
            clickListener(task)
        }
    }
}