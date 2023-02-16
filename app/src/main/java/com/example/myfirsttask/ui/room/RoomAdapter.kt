package com.example.myfirsttask.ui.room

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirsttask.R
import com.example.myfirsttask.data.model.room.RoomModelItemModel
import com.example.myfirsttask.databinding.ItemRoomBinding

class RoomAdapter(
    val roomList: ArrayList<RoomModelItemModel>, val clickListener: (RoomModelItemModel) -> Unit
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {


    inner class ViewHolder(private val view: ItemRoomBinding) : RecyclerView.ViewHolder(view.root) {

        fun setup(roomModelItemModel: RoomModelItemModel) {
            with(itemView) {
                var isOccupied = roomModelItemModel.isOccupied
                val roomNotOccupiedString = context.getString(R.string.room_not_occupied)
                val roomOccupiedString = context.getString(R.string.room_is_occupied)
                if (isOccupied == false) {
                    view.tvIsOccupied.text = roomNotOccupiedString
                    view.tvIsOccupied.setTextColor(Color.parseColor("#378805"))
                } else {
                    view.tvIsOccupied.text = roomOccupiedString
                    view.tvIsOccupied.setTextColor(Color.parseColor("#FF0000"))
                }
                view.tvRoomTitle.text =
                    "${context.getString(R.string.room_information)} ${roomModelItemModel.id}"
            }


            view.tvRoomNumber.text = "Room Number: ${roomModelItemModel.id}"
            view.tvMaxOccupancy.text =
                "Max Occupancy: ${roomModelItemModel.maxOccupancy.toString()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(

        ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun getItemCount(): Int = roomList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(roomList[position])
    }
}