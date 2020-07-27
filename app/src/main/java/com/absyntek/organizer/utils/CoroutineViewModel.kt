package com.absyntek.organizer.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class CoroutineViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    protected val ioScope = CoroutineScope(Dispatchers.IO)
    protected val uiScope = CoroutineScope(Dispatchers.Main.immediate + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}