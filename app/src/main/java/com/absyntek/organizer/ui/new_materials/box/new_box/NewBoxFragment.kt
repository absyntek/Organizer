package com.absyntek.organizer.ui.new_materials.box.new_box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.absyntek.organizer.R
import com.absyntek.organizer.databinding.FragmentNewBoxBinding
import com.absyntek.organizer.models.base.BoxUtilityEnum
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.utils.viewModelFactory

class NewBoxFragment : Fragment(){
    companion object{
        fun create() =
            NewBoxFragment()
    }

    private lateinit var bind: FragmentNewBoxBinding
    private lateinit var viewModel: NewBoxViewModel

    private lateinit var adapter: ArrayAdapter<String>

    private var tmp = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory {
            NewBoxViewModel(requireContext(), ::finish)
        }).get(NewBoxViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentNewBoxBinding.inflate(layoutInflater,container,false)
        setUpObservers()
        setUpListeners()
        return bind.root
    }

    private fun setUpListeners() {
        bind.btnAddBox.setOnClickListener {
            if (bind.layoutEdtNewBoxName.editText!!.text != null && bind.layoutEdtNewBoxUtility.editText!!.text != null){
                var utility:BoxUtilityEnum? = null
                BoxUtilityEnum.values().forEach { if (getString(it.stringResource) == bind.layoutEdtNewBoxUtility.editText!!.text.toString()) utility = it }
                utility?.let {
                    viewModel.createBox(BoxModel(0,bind.layoutEdtNewBoxName.editText!!.text.toString() , it.ordinal))
                }
            }
        }
    }

    private fun setUpObservers() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val items = BoxUtilityEnum.values().map { getString(it.stringResource) }
        adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (bind.layoutEdtNewBoxUtility.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun finish(){
        bind.layoutSystem.visibility = View.VISIBLE
        bind.recyclerSystems.visibility = View.VISIBLE
    }
}