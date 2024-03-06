package com.example.kotlinday5.listData

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinday5.R
import com.example.kotlinday5.main.Communicator
import com.example.kotlinday5.model.Products

class Adapter(val communicator: Communicator) : ListAdapter<Products, Adapter.ViewHolder>(
    ProductDiff()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObject: Products = getItem(holder.adapterPosition)
        holder.tvTitle.text = currentObject.title
        Glide.with(holder.itemView).load(currentObject.url).override(700, 700)
            .into(holder.ImageLogo)
        holder.itemView.setOnClickListener(){
            communicator.respond(currentObject)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ImageLogo = itemView.findViewById<ImageView>(R.id.iv_logo)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
    }
}


class ProductDiff : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }

}

