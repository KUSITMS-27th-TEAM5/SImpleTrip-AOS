package com.kusitms.ovengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.TicketData

class StorageDetailAdapter (
    private val ticketList: MutableList<TicketData> = mutableListOf(),
    private val clickListener:(TicketData)->Unit
) : RecyclerView.Adapter<StorageDetailViewHolder>(){

    fun updateList(newList: List<TicketData>) {
        ticketList.clear()
        ticketList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageDetailViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_store_detail,parent,false)
        return StorageDetailViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StorageDetailViewHolder, position: Int) {
        val ticketList = ticketList[position]
        holder.bind(ticketList, clickListener)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

}
class StorageDetailViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val title : TextView = view.findViewById<TextView>(R.id.title)
    //private val img : ImageView = view.findViewById(R.id.img)

    fun bind(task: TicketData, clickListener: (TicketData) -> Unit) {
        title.text = task.title

        view.setOnClickListener {
            clickListener(task)
        }
    }
}
