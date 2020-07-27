package com.absyntek.organizer.models.materials

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.absyntek.organizer.models.base.BoxSizeEnum
import com.absyntek.organizer.models.base.BoxUtilityEnum

@Entity
data class BoxModel(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    var name: String,
    var utility: Int
)