package com.kaveri.jetpackcomponentdemo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.databinding.FragmentMyHomeBinding
import com.kaveri.jetpackcomponentdemo.navigators.MainNavigator
import com.kaveri.jetpackcomponentdemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MyHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyHomeFragment : Fragment() {

    private val TAG = "MyHomeFragment"
    private lateinit var binding: FragmentMyHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_home, container, false)
        binding = FragmentMyHomeBinding.bind(view)
        //require activity always returns an activity and not null.
        //If the activity is not attached to the fragment, throws Illegal argument exception
        //mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.myHomeFragment_to_mySecondFragment)
        }
        binding.uploadButton.setOnClickListener {
            getSharedViewModel<MainViewModel<MainNavigator>>().uploadData()
        }
    }

    override fun onResume(){
        Log.d(TAG, "onResume")
        super.onResume()
        updateViewModelData()
        setListeners()
    }

    private fun updateViewModelData() {
        //getSharedViewModel<MainViewModel<MainNavigator>>().screenName.value = getString(R.string.my_home_fragment)
    }

    private fun setListeners() {
        getSharedViewModel<MainViewModel<MainNavigator>>().screenName.observe(requireActivity(),
            Observer {
                Log.d(TAG, "screen $it")
            })
        getSharedViewModel<MainViewModel<MainNavigator>>().messageFRomService.observe( requireActivity(),
            Observer {
            binding.statusMsgTv.text = it
            })
    }


}