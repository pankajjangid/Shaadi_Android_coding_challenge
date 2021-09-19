package com.example.demoproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoproject.model.entites.Invitation


@Database(entities = [Invitation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun invitationDao(): InvitationDao

    companion object {

        @Volatile
        var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "invitation.db"
        ).build()
    }
}