package com.absyntek.organizer.ui.new_materials.box.edit_box

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.absyntek.organizer.R
import com.absyntek.organizer.databinding.FragmentEditBoxBinding
import com.absyntek.organizer.models.base.BoxUtilityEnum
import com.absyntek.organizer.models.base.SystemKindEnum
import com.absyntek.organizer.models.materials.SystemKind
import com.absyntek.organizer.utils.Destination
import com.absyntek.organizer.utils.viewModelFactory

class EditBoxFragment :Fragment() {

    private lateinit var data: Destination.EditBox

    companion object {
        fun create(data: Destination.EditBox) = EditBoxFragment().apply {
            this.data = data
        }
    }

    private lateinit var bind: FragmentEditBoxBinding
    private lateinit var viewModel: EditBoxViewModel

    private val systemAdapter = EditBoxSystemAdapter()

    private var boxId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boxId = data.boxId
        if (boxId == null) {
            requireActivity().finish()
        }
        viewModel = ViewModelProvider(this, viewModelFactory {
            EditBoxViewModel(requireContext(), boxId!!, ::finish)
        }).get(EditBoxViewModel::class.java)

        systemAdapter.addBox = ::creatSys
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEditBoxBinding.inflate(layoutInflater, container, false)
        setUpObserver()
        setUpListeners()
        return bind.root
    }

    private fun setUpObserver() {
        viewModel.systems.observe(viewLifecycleOwner, Observer {
            systemAdapter.updateList(it)
        })
        viewModel.box.observe(viewLifecycleOwner, Observer { box ->
            box?.let {
                bind.layoutEdtNewBoxName.editText!!.text = Editable.Factory().newEditable(it.name)
                selectBoxUtility(it.utility)
                bind.layoutMain.visibility = View.VISIBLE
                viewModel.getSystems(it.id)
            }
        })
    }

    private fun setUpListeners() {
        bind.btnAddBox.setOnClickListener {
            if (bind.layoutEdtNewBoxName.editText!!.text != null) {
                viewModel.box.value?.let { box ->
                    box.name = bind.layoutEdtNewBoxName.editText!!.text.toString()
                    box.utility = when (bind.chipGroupBoxUsage.checkedChipId){
                        R.id.dry -> 0
                        R.id.bloom -> 1
                        R.id.grow -> 2
                        R.id.all -> 3
                        else -> 3
                    }
                    viewModel.upDateBox(box)
                }
                finish()
            }
        }

        bind.btnDelete.setOnClickListener {
            viewModel.deleteBox()
        }
    }

    private fun selectBoxUtility(id: Int) {
        when (id) {
            0 -> {
                bind.chipGroupBoxUsage.check(R.id.dry)
            }
            1 -> {
                bind.chipGroupBoxUsage.check(R.id.bloom)
            }
            2 -> {
                bind.chipGroupBoxUsage.check(R.id.grow)
            }
            3 -> {
                bind.chipGroupBoxUsage.check(R.id.all)
                bind.chipGroupBoxUsage.check(R.id.dry)
                bind.chipGroupBoxUsage.check(R.id.bloom)
                bind.chipGroupBoxUsage.check(R.id.grow)
            }
            else -> {
            }
        }
    }

    private fun creatSys() {
        viewModel.addSystem(SystemKind(0, boxId!!, "test", SystemKindEnum.BLOOM.ordinal, 40, 10))
    }

    private fun finish(){
        requireActivity().finish()
    }
}