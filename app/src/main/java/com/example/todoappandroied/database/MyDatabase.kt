package com.example.todoappandroied.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoappandroied.database.daos.TasksDao
import com.example.todoappandroied.database.models.Task

@Database(entities = [Task::class],
        version = 1,
    exportSchema = false
    )
abstract class MyDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object{
        val DATABASE_NAME = "TaskDatabase "
        private var myDatabase : MyDatabase? = null
        fun getDatabase(context : Context) : MyDatabase{
            if (myDatabase == null){
                myDatabase = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return myDatabase!!
        }
    }

}