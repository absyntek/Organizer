package com.absyntek.organizer.ui.new_materials.box.new_box

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.absyntek.organizer.database.repository.BoxRepository
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.utils.CoroutineViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class NewBoxViewModel(context: Context, val finish: () -> Unit) : CoroutineViewModel(){

    private val boxRepository = BoxRepository(context)
    var allBoxes: LiveData<List<BoxModel>>

    init {
        allBoxes = boxRepository.getAllBoxes
    }

    fun createBox(box: BoxModel){
        ioScope.launch {
            try {
                boxRepository.addBoxes(box)
                finish()
            }catch (e:Exception){

            }
        }
    }
}