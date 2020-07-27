package com.absyntek.organizer.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.absyntek.organizer.models.materials.BoxModel

@Dao
interface BoxDao {
    @Query("SELECT * FROM boxmodel")
    fun getAll(): LiveData<List<BoxModel>>

    @Query("SELECT * FROM boxmodel WHERE id == :id")
    fun getBox(id:Int): LiveData<BoxModel?>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addBox(box: BoxModel)

    @Update
    fun upDateBox(box: BoxModel)

    @Delete
    fun delete(box: BoxModel)
}