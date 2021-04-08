package com.kaveri.jetpackcomponentdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.dao.ListItem
import com.kaveri.jetpackcomponentdemo.viewholders.BasicViewHolder
import com.kaveri.jetpackcomponentdemo.viewholders.HeadingViewHolder
import com.kaveri.jetpackcomponentdemo.viewholders.NameViewHolder

class ComplexListAdapter( var items : List<ListItem>) : RecyclerView.Adapter<BasicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        return when(viewType) {
            nameItem -> {
                NameViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.name_layout, parent, false))
            } else -> {
                HeadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.heading_layout, parent, false))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is ListItem.Name -> {
                nameItem
            }
            is ListItem.HeadLine -> {
                headerItem
            }
        }
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
         when(holder) {
             is NameViewHolder -> {
                val item : ListItem.Name = items[position] as ListItem.Name
                holder.firstNameTv.text = item.firstName
                holder.lastNameTv.text = item.lastName
             }
             is HeadingViewHolder -> {
                val item : ListItem.HeadLine = items[position] as ListItem.HeadLine
                holder.headerTv.text = item.header
             }
         }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object {
        const val nameItem = 2
        const val headerItem = 1
    }
}