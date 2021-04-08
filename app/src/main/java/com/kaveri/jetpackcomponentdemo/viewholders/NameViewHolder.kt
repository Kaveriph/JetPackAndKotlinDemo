package com.kaveri.jetpackcomponentdemo.viewholders

import android.view.View
import android.widget.TextView
import com.kaveri.jetpackcomponentdemo.R
import kotlinx.android.synthetic.main.name_layout.view.*

class NameViewHolder(itemView: View) : BasicViewHolder(itemView) {
    val firstNameTv = itemView.findViewById<TextView>(R.id.firstNameTv)
    val lastNameTv = itemView.findViewById<TextView>(R.id.lastNameTv)
}