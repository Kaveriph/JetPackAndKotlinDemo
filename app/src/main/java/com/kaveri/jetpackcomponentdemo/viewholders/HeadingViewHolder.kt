package com.kaveri.jetpackcomponentdemo.viewholders

import android.view.View
import android.widget.TextView
import com.kaveri.jetpackcomponentdemo.R

class HeadingViewHolder(itemView: View) : BasicViewHolder(itemView) {
    val headerTv = itemView.findViewById<TextView>(R.id.headlineTv)
}