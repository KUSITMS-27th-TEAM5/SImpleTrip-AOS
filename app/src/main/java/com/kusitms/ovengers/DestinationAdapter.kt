package com.kusitms.ovengers

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.Destination

class DestinationAdapter(private val dataSet : ArrayList<Destination>) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>(){
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {// 커밋용
        var destination : TextView
        var img : ImageView

        init {
            destination = view.findViewById(R.id.destination)
            img = view.findViewById(R.id.img_destination)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.destination_rv_item,parent,false)
    return ViewHolder(view)
    }
    interface ItemClick {
        fun onClick(view: View,position: Int)
    }
    var itemClick : ItemClick? = null

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.destination.text = dataSet[position].destination
        holder.img.setImageResource(dataSet[position].img)

        if(itemClick != null) {
            holder?.itemView!!.setOnClickListener{v ->
                itemClick!!.onClick(v,position)
            }
        }
    }

} // 커밋용