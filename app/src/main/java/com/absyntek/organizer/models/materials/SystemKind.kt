package com.absyntek.organizer.models.materials

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.absyntek.organizer.models.base.SystemKindEnum

@Entity(foreignKeys = [ForeignKey(entity = BoxModel::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("id"),
    onDelete = ForeignKey.NO_ACTION)]
)
data class SystemKind(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val boxId: Int,
    val name: String,
    val systemKind: Int,
    val waterLiter: Int,
    val maxPlantSize:Int
)