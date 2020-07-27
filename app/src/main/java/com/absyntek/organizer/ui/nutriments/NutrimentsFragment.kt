package com.absyntek.organizer.ui.nutriments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.absyntek.organizer.databinding.FragmentNutrimentsBinding
import com.absyntek.organizer.utils.viewModelFactory

class NutrimentsFragment : Fragment(){

    private lateinit var bind:FragmentNutrimentsBinding
    private lateinit var viewModel: NutrimentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory{
            NutrimentsViewModel()
        }).get(NutrimentsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bind = FragmentNutrimentsBinding.inflate(layoutInflater, container, false)
        return bind.root
    }
}