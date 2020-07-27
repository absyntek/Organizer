package com.absyntek.organizer.ui.new_materials.box.edit_box

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.absyntek.organizer.databinding.FragmentEditBoxBinding
import com.absyntek.organizer.models.base.BoxUtilityEnum
import com.absyntek.organizer.utils.Destination
import com.absyntek.organizer.utils.viewModelFactory

class EditBoxFragment :Fragment(){

    private lateinit var data: Destination.EditBox

    companion object{
        fun create(data: Destination.EditBox) = EditBoxFragment().apply {
            this.data = data
        }
    }

    private lateinit var bind: FragmentEditBoxBinding
    private lateinit var viewModel: EditBoxViewModel

    private var boxId:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boxId = data.boxId
        if (boxId == null) {
            requireActivity().finish()
        }
        viewModel = ViewModelProvider(this, viewModelFactory{
            EditBoxViewModel(requireContext(), boxId!!, ::finish)
        }).get(EditBoxViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEditBoxBinding.inflate(layoutInflater,container,false)
        setUpObserver()
        setUpListeners()
        return bind.root
    }

    private fun setUpObserver() {
        viewModel.box.observe(viewLifecycleOwner, Observer {box ->
            box?.let {
                bind.layoutEdtNewBoxName.editText!!.text = Editable.Factory().newEditable(it.name)
                val boxUse = BoxUtilityEnum.values()[it.utility]
                bind.layoutEdtNewBoxUtility.editText!!.text = Editable.Factory().newEditable(getString(boxUse.stringResource))
                bind.layoutMain.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpListeners() {
        bind.btnAddBox.setOnClickListener {_ ->
            if (bind.layoutEdtNewBoxName.editText!!.text != null && bind.layoutEdtNewBoxUtility.editText!!.text != null){
                var utility:BoxUtilityEnum? = null
                BoxUtilityEnum.values().forEach { bu -> if (getString(bu.stringResource) == bind.layoutEdtNewBoxUtility.editText!!.text.toString()) utility = bu }
                utility?.let {utility ->
                    viewModel.box.value?.let {box ->
                        box.name = bind.layoutEdtNewBoxName.editText!!.text.toString()
                        box.utility = utility.ordinal
                        viewModel.upDateBox(box)
                    }
                }
            }
        }

        bind.btnDelete.setOnClickListener {
            viewModel.deleteBox()
        }
    }

    private fun finish(){
        requireActivity().finish()
    }
}