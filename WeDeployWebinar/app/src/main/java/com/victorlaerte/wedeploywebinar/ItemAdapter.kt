package com.victorlaerte.wedeploywebinar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author Victor Oliveira
 */
class ItemAdapter(var items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.name.text = items[position].name
        holder.description.text = items[position].description
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.nameTextView)
        var description: TextView = itemView.findViewById(R.id.descriptionTextView)
    }
}