package com.absyntek.organizer.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.absyntek.organizer.R
import com.absyntek.organizer.databinding.FragmentDashboardBinding
import com.absyntek.organizer.ui.home.HomeViewModel
import com.absyntek.organizer.utils.viewModelFactory

class DashboardFragment : Fragment() {

    private lateinit var bind: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory{
            DashboardViewModel()
        }).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bind = FragmentDashboardBinding.inflate(inflater,container,false)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            bind.textDashboard.text = it
        })
        return bind.root
    }
}