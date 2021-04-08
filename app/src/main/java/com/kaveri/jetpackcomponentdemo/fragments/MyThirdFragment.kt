package com.kaveri.jetpackcomponentdemo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.databinding.FragmentMyThirdBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class MyThirdFragment : Fragment() {

    private lateinit var binding: FragmentMyThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentMyThirdBinding.bind(inflater.inflate(R.layout.fragment_my_third, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.prevButton.setOnClickListener {
            findNavController().navigate(R.id.myThirdFragment_to_mySecondFragment)
        }
        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.myThirdFragment_to_myHomeFragment)
        }
    }

}