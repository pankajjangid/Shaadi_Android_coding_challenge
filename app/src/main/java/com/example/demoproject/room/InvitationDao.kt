package com.example.demoproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.demoproject.model.entites.Invitation

@Dao
interface InvitationDao {
    @Query("SELECT * FROM invitation")
    fun getAll(): List<Invitation>

    @Insert
    fun insertAll(vararg users: Invitation)

    @Update
    fun update(user: Invitation)
}