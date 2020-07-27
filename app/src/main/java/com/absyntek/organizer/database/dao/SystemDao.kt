package com.absyntek.organizer.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.absyntek.organizer.models.materials.SystemKind

@Dao
interface SystemDao {
    @Query("SELECT * FROM systemkind")
    fun getAll(): LiveData<List<SystemKind>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addSystems(systemKind: SystemKind)

    @Delete
    fun delete(systemKind: SystemKind)
}