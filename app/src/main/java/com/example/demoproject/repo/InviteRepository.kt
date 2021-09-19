package com.example.demoproject.repo

import com.example.demoproject.model.entites.Invitation
import com.example.demoproject.model.network.InviteResponse
import com.example.demoproject.networking.MyApi
import com.example.demoproject.networking.SafeApiRequest
import com.example.demoproject.room.AppDatabase

class InviteRepository(private val myApi: MyApi, private val db: AppDatabase) : SafeApiRequest() {
    suspend fun getUserInvites(result: String): InviteResponse {
        return apiRequest { myApi.getInvitation(result) }
    }

    suspend fun addInvites(invitations: List<InviteResponse.Result>) {

        db.invitationDao().insertAll(*invitations.map {
            Invitation.convertFromRemote(it)
        }.toTypedArray())

    }

    suspend fun updateInvitation(invitation: InviteResponse.Result) {

        db.invitationDao().update(Invitation.convertFromRemote(invitation))


    }

     suspend fun getLocalInvites(): InviteResponse {
        val items = db.invitationDao().getAll()
        return InviteResponse(null, items.map {
            InviteResponse.Result.convertFromLocal(it.data!!)
        })
    }






}