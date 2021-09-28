package com.example.repasoprueba

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cafe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cafes() : CafeDAO

    companion object{
        fun getDatabase(descripcionCafe: descripcionCafe): AppDatabase {

        }

        @Volatile
        private var INSTANCE : AppDatabase? = null
    }

    fun getDatebase(context: Context) : AppDatabase{
        val tempInstace = INSTANCE

        if (tempInstace != null){
            return tempInstace
        }
        synchronized(this){
            val instace = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "app_database").build()
                INSTANCE = instace
            return instace
        }
    }



}