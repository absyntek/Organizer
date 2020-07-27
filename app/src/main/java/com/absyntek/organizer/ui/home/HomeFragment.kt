package com.absyntek.organizer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.absyntek.organizer.R
import com.absyntek.organizer.databinding.FragmentHomeBinding
import com.absyntek.organizer.ui.notifications.NotificationsViewModel
import com.absyntek.organizer.utils.viewModelFactory

class HomeFragment : Fragment() {

    private lateinit var bind: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory{
            HomeViewModel(requireContext())
        }).get(HomeViewModel::class.java)

        adapter = HomeAdapter(requireContext())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHomeBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpObservers()
    }
    private fun setUpUi(){
        bind.mainRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        bind.mainRecycler.adapter = adapter
    }
    private fun setUpObservers(){
        viewModel.allBoxes.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                adapter.updateList(it)
            }else{
                adapter.updateList(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBoxes()
    }
}