package com.absyntek.organizer.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.absyntek.organizer.database.DataBase
import com.absyntek.organizer.models.materials.BoxModel

class BoxRepository (context: Context){
    private val boxDao = DataBase.getDatabase(context.applicationContext).boxDao()

    val getAllBoxes = boxDao.getAll()

    fun getBox(boxId: Int)= boxDao.getBox(boxId)

    fun addBoxes(vararg box:BoxModel) {
        box.forEach {
            boxDao.addBox(it)
        }
    }

    fun updateBox(box: BoxModel){
        boxDao.upDateBox(box)
    }

    fun deleteBox(vararg box:BoxModel){
        box.forEach {
            boxDao.delete(it)
        }
    }
}