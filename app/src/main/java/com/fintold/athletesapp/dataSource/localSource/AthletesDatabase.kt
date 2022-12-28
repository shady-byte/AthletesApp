package com.fintold.athletesapp.dataSource.localSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fintold.athletesapp.dataSource.dataClasses.Athlete


@Database(entities = [Athlete::class], version = 2, exportSchema = false)
abstract class AthletesDatabase: RoomDatabase() {
    abstract val databaseDao: DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AthletesDatabase?=null

        fun getInstance(context: Context) : AthletesDatabase {
            synchronized(lock = this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AthletesDatabase::class.java, "athletes_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}