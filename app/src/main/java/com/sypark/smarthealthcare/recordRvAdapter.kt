package com.sypark.smarthealthcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recordRvAdapter(val dataList:MutableList<recordDataModel>):RecyclerView.Adapter<recordRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recordRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_rv_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: recordRvAdapter.ViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(item:recordDataModel){
            val date = itemView.findViewById<TextView>(R.id.date)
            val kind = itemView.findViewById<TextView>(R.id.exKind)

            date.text = "${item.year}-${item.month}-${item.day}"
            kind.text = item.kind
        }
    }
}