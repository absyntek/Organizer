package com.absyntek.organizer.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.absyntek.organizer.database.DataBase
import com.absyntek.organizer.database.dao.BoxDao
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.models.materials.SystemKind

class SystemRepository (context: Context){
    private val sysDao = DataBase.getDatabase(context.applicationContext).systemDao()

    val getAllSystems = sysDao.getAll()

    fun addSystem(vararg sys:SystemKind) {
        sys.forEach {
            sysDao.addSystems(it)
        }
    }

    fun deleteSystem(vararg sys:SystemKind){
        sys.forEach {
            sysDao.delete(it)
        }
    }
}