package com.kaveri.jetpackcomponentdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.databinding.FragmentMySecondBinding
import com.kaveri.jetpackcomponentdemo.navigators.MainNavigator
import com.kaveri.jetpackcomponentdemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MySecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MySecondFragment : Fragment() {

    private lateinit var binding: FragmentMySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMySecondBinding.bind(inflater.inflate(R.layout.fragment_my_second, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.mySecondFragment_to_myThirdFragment)
        }
        binding.uploadButton.setOnClickListener {
            getSharedViewModel<MainViewModel<MainNavigator>>().uploadDataViaBoundservice()
        }
        getSharedViewModel<MainViewModel<MainNavigator>>().messageFRomService.observe( requireActivity(), Observer { it ->
            binding.statusMsgTv.text = it
        })
    }

}