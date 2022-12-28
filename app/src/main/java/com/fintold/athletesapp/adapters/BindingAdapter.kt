package com.fintold.athletesapp.adapters

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fintold.athletesapp.R
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.squareup.picasso.Picasso

@BindingAdapter("RecyclerViewAdapter")
fun RecyclerView.bindAdapter(athletes: List<Athlete>?) {
    visibility = if(athletes.isNullOrEmpty()) {
        View.GONE
    } else {
        athletes.let {
            val adapter = this.adapter as RecyclerViewAdapter
            adapter.submitList(athletes)
        }
        View.VISIBLE
    }
}

@BindingAdapter("ProgressBarAdapter")
fun ProgressBar.bindAdapter(athletes: List<Athlete>?) {
    visibility = if(athletes.isNullOrEmpty()) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("ImageAdapter")
fun ImageView.bindAdapter(url: String) {
    val imageUri = Uri.parse(url)
    Picasso.get()
        .load(imageUri)
        .placeholder(R.drawable.ic_person_icon)
        .error(R.drawable.ic_person_icon)
        .into(this)
}
