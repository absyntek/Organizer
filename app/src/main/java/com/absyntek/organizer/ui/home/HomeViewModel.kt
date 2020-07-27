package com.absyntek.organizer.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.absyntek.organizer.database.repository.BoxRepository
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.utils.CoroutineViewModel
import kotlinx.coroutines.launch

class HomeViewModel(val context: Context) :CoroutineViewModel() {

    private val boxRepository = BoxRepository(context)
    var allBoxes: LiveData<List<BoxModel>>

    init {
        allBoxes = boxRepository.getAllBoxes
    }

    fun getAllBoxes() {
        ioScope.launch {
            allBoxes = boxRepository.getAllBoxes
        }
    }
}