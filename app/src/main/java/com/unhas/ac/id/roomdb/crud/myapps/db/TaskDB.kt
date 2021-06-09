package com.unhas.ac.id.roomdb.crud.myapps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1,entities = [Task::class])
abstract class TaskDB : RoomDatabase() {
    abstract fun taskDao() : TaskDAO
    companion object {
        @Volatile
        private var INSTANCE: TaskDB? = null

        fun getInstance(context: Context): TaskDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDB::class.java,
                        "task_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}