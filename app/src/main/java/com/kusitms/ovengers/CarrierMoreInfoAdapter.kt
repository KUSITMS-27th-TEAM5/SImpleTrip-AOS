package com.kusitms.ovengers

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.TicketData
import com.kusitms.ovengers.data.carrierMoreInfo

class CarrierMoreInfoAdapter (// 커밋용
    private val ticketList: MutableList<TicketData> = mutableListOf(),

    ) : RecyclerView.Adapter<CarrierMoreInfoAdapter.ViewHolder>(){

    fun updateList(newList: List<TicketData>) {
        ticketList.clear()
        ticketList.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carrierinfo_rv_item,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
//        var destinationImg : ImageView = view.findViewById(R.id.img_ticket)
       private var ticketName : TextView = view.findViewById(R.id.ticketName)

        fun bind(task : TicketData) {
            ticketName.text = task.title
//            destinationImg.setImageResource(task.)
        }

//        init{
//            destinationImg = view.findViewById(R.id.img_ticket)
//            ticketName = view.findViewById(R.id.ticketName)
//        }

    }
    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick?=null

    interface ItemLongClick {
        fun onLongClick(view: View,position: Int)
    }
    var itemLongClick : CarrierAdapter.ItemLongClick?=null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.destinationImg.setImageResource(dataSet[position].img)
//        holder.ticketName.setText("티켓 이름을 입력해주세요")

        val ticketList = ticketList[position]
        holder.bind(ticketList)

        if(itemClick != null) {
            holder?.itemView!!.setOnClickListener{v ->
                itemClick!!.onClick(v,position)
            }
        }

        if (itemLongClick !=null) {
            holder?.itemView!!.setOnLongClickListener {v->
                itemLongClick!!.onLongClick(v,position)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }


} // 커밋용


