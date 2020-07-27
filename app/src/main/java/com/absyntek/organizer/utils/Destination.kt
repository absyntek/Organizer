package com.absyntek.organizer.utils

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.absyntek.organizer.ui.new_materials.box.edit_box.EditBoxFragment
import com.absyntek.organizer.ui.new_materials.box.new_box.NewBoxFragment
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

sealed class Destination: Serializable{
    open fun toFragment(): Fragment? = null
    open fun toIntent(context: Context): Intent? = null

    @Parcelize
    object NewBox : Destination(), Parcelable {
        override fun toFragment() = NewBoxFragment.create()
    }

    @Parcelize
    class EditBox(val boxId: Int) : Destination(), Parcelable{
        override fun toFragment() = EditBoxFragment.create(this)
    }
}