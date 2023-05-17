package com.kusitms.ovengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.RequestMakeCarrier

class CarrierAdapter(private val dataSet : ArrayList<RequestMakeCarrier>) : RecyclerView.Adapter<CarrierAdapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val carrierName : TextView

        init {
            carrierName = view.findViewById(R.id.textview_carrier_name)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrierAdapter.ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carrierName.text = dataSet[position].carrierName

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
        return dataSet.size
    }
}