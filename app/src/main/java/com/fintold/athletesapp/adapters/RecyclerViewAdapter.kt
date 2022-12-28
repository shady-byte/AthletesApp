package com.fintold.athletesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.databinding.AthleteItemLayoutBinding

class RecyclerViewAdapter(private val clickListener: OnClickListener): ListAdapter<Athlete,RecyclerViewAdapter.ViewHolder>(AthleteCallBack) {

    companion object AthleteCallBack: DiffUtil.ItemCallback<Athlete>() {
        override fun areItemsTheSame(oldItem: Athlete, newItem: Athlete): Boolean {
            return oldItem.name == newItem.name && oldItem.brief == newItem.brief
        }

        override fun areContentsTheSame(oldItem: Athlete, newItem: Athlete): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: AthleteItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(athlete: Athlete) {
            binding.athleteSelected = athlete
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AthleteItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val athlete = getItem(position)
        holder.bind(athlete)
        holder.itemView.setOnClickListener {
            clickListener.onClick(athlete)
        }
    }

}

class OnClickListener(val clickListener: (athlete: Athlete) ->Unit) {
    fun onClick(athlete: Athlete) = clickListener(athlete)
}