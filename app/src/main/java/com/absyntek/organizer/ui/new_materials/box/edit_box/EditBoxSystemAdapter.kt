package com.absyntek.organizer.ui.new_materials.box.edit_box

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.absyntek.organizer.databinding.BoxSystemItemBinding
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.models.materials.SystemKind

class EditBoxSystemAdapter : RecyclerView.Adapter<SystemViewHolder>() {

    private var sysList:List<SystemKind> = listOf()

    lateinit var addBox: () -> Unit

    fun updateList(sysList:List<SystemKind>){
        this.sysList = sysList
        notifyDataSetChanged()
    }

    override fun getItemCount() = sysList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemViewHolder {
        val root = FrameLayout(parent.context)
        root.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return  SystemViewHolder(BoxSystemItemBinding.inflate(LayoutInflater.from(root.context), root, false))
    }

    override fun onBindViewHolder(holder: SystemViewHolder, position: Int) {
        val bind = holder.bind
        val item = sysList[position]
        if (item.id == -1 ){
            bind.layoutMore.visibility = View.VISIBLE
            bind.layoutSys.visibility = View.GONE
            bind.layoutMore.setOnClickListener {
                addBox()
            }
        }else{
            bind.layoutMore.visibility = View.GONE
            bind.layoutSys.visibility = View.VISIBLE
        }
    }

}

class SystemViewHolder(val bind: BoxSystemItemBinding): RecyclerView.ViewHolder(bind.root)