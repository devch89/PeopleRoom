package com.example.myfirsttask.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirsttask.R
import com.example.myfirsttask.databinding.ItemPeopleBinding
import com.example.myfirsttask.data.model.people.PeopleModelItemModel

class PeopleAdapter(
    val peopleList: ArrayList<PeopleModelItemModel>,
    val clickListener: (PeopleModelItemModel) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {


    inner class ViewHolder(private val view: ItemPeopleBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun setup(peopleModelItemModel: PeopleModelItemModel) {
            with(itemView) {
                Glide.with(context).load(peopleModelItemModel.avatar)
                    .placeholder(R.drawable.ic_loading).centerCrop()
                    .into(view.ivUserPic)

                view.root.setOnClickListener {
                    clickListener(peopleModelItemModel)
                }
            }
            view.tvJobTitle.text = peopleModelItemModel.jobtitle
            //   view.tvName.text = "${peopleModelItemModel.firstName} ${peopleModelItemModel.lastName}"
            // view.tvEmail.text = peopleModelItemModel.email
            // view.tvFavoriteColor.text =
            //      "Fun Fact: My favorite color is " + "${peopleModelItemModel.favouriteColor}."

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPeopleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun getItemCount(): Int = peopleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(peopleList[position])
    }
}