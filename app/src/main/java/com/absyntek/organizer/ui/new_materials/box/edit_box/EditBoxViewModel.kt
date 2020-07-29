package com.absyntek.organizer.ui.new_materials.box.edit_box

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.absyntek.organizer.database.repository.BoxRepository
import com.absyntek.organizer.database.repository.SystemRepository
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.models.materials.SystemKind
import com.absyntek.organizer.models.materials.emptySystem
import com.absyntek.organizer.utils.CoroutineViewModel
import kotlinx.coroutines.launch

class EditBoxViewModel(
    context: Context,
    val boxId: Int,
    val finish: () -> Unit
) : CoroutineViewModel(){

    private val boxRepository = BoxRepository(context)
    private val sysRepository = SystemRepository(context)
    var box: LiveData<BoxModel?>
    var systems: LiveData<List<SystemKind>> = MutableLiveData(listOf(emptySystem()))

    init {
        box = boxRepository.getBox(boxId)
    }

    fun getSystems(boxId: Int) {
        ioScope.launch {
            val systemsB = sysRepository.getAllSystemsWithId(boxId)
            if (systemsB.value.isNullOrEmpty()){
                systems = MutableLiveData(listOf(emptySystem()))
            }else{
                systems = systemsB
            }
        }
    }

    fun addSystem(systemKind: SystemKind){
        ioScope.launch {
            sysRepository.addSystem(systemKind)
        }
    }

    fun deleteBox(){
        ioScope.launch {
            box.value?.let {
                boxRepository.deleteBox(it)
                finish()
            }
        }
    }

    fun upDateBox(box: BoxModel){
        ioScope.launch {
            boxRepository.updateBox(box)
        }
    }
}