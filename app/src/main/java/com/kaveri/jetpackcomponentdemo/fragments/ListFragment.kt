 package com.kaveri.jetpackcomponentdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.adapters.ComplexListAdapter
import com.kaveri.jetpackcomponentdemo.dao.ListItem

 /**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val listItems = arrayListOf<ListItem>()
        listItems.add(ListItem.HeadLine("Ladies"))
        listItems.add(ListItem.Name("Arpana", "Nithin"))
        listItems.add(ListItem.Name("Kaveri", "Harsha"))
        listItems.add(ListItem.Name("Anusha", "Naveen"))

        listItems.add(ListItem.HeadLine("Gentlemen"))
        listItems.add(ListItem.Name("Nithin", "Mise"))
        listItems.add(ListItem.Name("Harsha", "Kaulase"))
        listItems.add(ListItem.Name("Naveen", "Mise"))
        recyclerView.adapter = ComplexListAdapter(listItems)
        (recyclerView.adapter as ComplexListAdapter).notifyDataSetChanged()
    }
}