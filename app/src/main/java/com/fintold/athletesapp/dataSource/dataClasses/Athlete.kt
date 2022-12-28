package com.fintold.athletesapp.dataSource.dataClasses

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Athletes_table", primaryKeys = ["name","brief"])
data class Athlete(
    val name: String,
    val image: String,
    val brief: String
): Parcelable

data class ResultOfAthletesApi(
    val athletes: List<Athlete>
)

