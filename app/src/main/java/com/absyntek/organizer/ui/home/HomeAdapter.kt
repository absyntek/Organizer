package com.absyntek.organizer.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.absyntek.organizer.NavigatorActivity
import com.absyntek.organizer.R
import com.absyntek.organizer.databinding.MainBoxItemBinding
import com.absyntek.organizer.models.base.BoxUtilityEnum
import com.absyntek.organizer.models.materials.BoxModel
import com.absyntek.organizer.utils.Destination

class HomeAdapter(val context: Context) : RecyclerView.Adapter<BoxItemVH>(){

    private var boxesList:List<BoxModel> = listOf()

    fun updateList(boxesList:List<BoxModel>){
        this.boxesList = boxesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxItemVH {
        val root = FrameLayout(parent.context)
        root.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return BoxItemVH(MainBoxItemBinding.inflate(LayoutInflater.from(root.context), root, false).root)
    }

    override fun getItemCount()= boxesList.size

    override fun onBindViewHolder(holder: BoxItemVH, position: Int) {
        val box = boxesList[position]
        holder.tvTitle.text = box.name
        val boxUse = BoxUtilityEnum.values()[box.utility]
        holder.tvBoxUse.text = context.getString(boxUse.stringResource)
        holder.imgSetting.setOnClickListener {
            startActivity(context, NavigatorActivity.newIntent(context, Destination.EditBox(box.id)),null)
        }
    }

}
class BoxItemVH(root:View): RecyclerView.ViewHolder(root){
    val tvTitle = root.findViewById<TextView>(R.id.tv_title_box)
    val tvBoxUse = root.findViewById<TextView>(R.id.tv_box_kind)
    val tvBoxNbrSys = root.findViewById<TextView>(R.id.tv_nbr_system)
    val imgWatch = root.findViewById<ImageView>(R.id.img_watch)
    val imgSetting = root.findViewById<ImageView>(R.id.img_setting)
}