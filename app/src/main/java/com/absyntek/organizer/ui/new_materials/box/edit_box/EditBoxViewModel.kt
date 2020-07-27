package com.absyntek.organizer.ui.new_materials.box.edit_box

import android.content.Context
import androidx.lifecycle.LiveData
import com.absyntek.organizer.database.repository.BoxRepository
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.utils.CoroutineViewModel
import kotlinx.coroutines.launch

class EditBoxViewModel(
    context: Context,
    val boxId: Int,
    val finish: () -> Unit
) : CoroutineViewModel(){

    private val boxRepository = BoxRepository(context)
    var box: LiveData<BoxModel?>

    init {
        box = boxRepository.getBox(boxId)
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