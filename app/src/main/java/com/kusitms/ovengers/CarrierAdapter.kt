package com.kusitms.ovengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.*
import java.time.LocalDate

class CarrierAdapter(
    private val carrierList: MutableList<Ddata> = mutableListOf(),

)
    : RecyclerView.Adapter<CarrierAdapter.ViewHolder>() {


    fun updateList(newList: List<Ddata>) {
        carrierList.clear()
        carrierList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carrier_rv_item,parent,false)

        return ViewHolder(view)
    }
    //click
    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick? = null

    //longClick
    interface ItemLongClick {
        fun onLongClick(view: View,position: Int)
    }
    var itemLongClick : ItemLongClick?=null

    //item click





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val carrierList = carrierList[position]
        holder.bind(carrierList)



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
        return carrierList.size
    }
     class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val carrierName : TextView = view.findViewById(R.id.textview_carrier_name)


         fun bind(task:Ddata){
             carrierName.text = task.name
         }

//        init {
//
//
//            carrierName = view.findViewById(R.id.textview_carrier_name)
//
//
//        }


    }
} // 커밋용