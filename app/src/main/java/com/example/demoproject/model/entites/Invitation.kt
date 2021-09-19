package com.example.demoproject.model.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.demoproject.model.network.InviteResponse
import com.google.gson.Gson

@Entity
data class Invitation(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "data") val data: String?,
) {
    companion object {
        fun convertFromRemote(invitation: InviteResponse.Result): Invitation {
            return Invitation(invitation.login.uuid, Gson().toJson(invitation))
        }
    }
}
