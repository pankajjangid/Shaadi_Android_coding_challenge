package com.example.demoproject.ui.invite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproject.model.network.InviteResponse
import com.example.demoproject.repo.InviteRepository
import com.example.demoproject.utils.Coroutines

class InviteViewModel(val  repository: InviteRepository) :ViewModel() {

    var listener: InviteListener? = null

    var inviteList = MutableLiveData<List<InviteResponse.Result>>()




    init {



    }



    fun requestInvites(result:String){

        listener?.onStarted()
        Coroutines.io {
            try {

                val response = repository.getUserInvites(result)
                repository.addInvites(response.results)
                val localInvites = repository.getLocalInvites()

                Coroutines.main {

                    listener?.onSuccess(localInvites)

                }
            } catch (e: Exception) {
                e.printStackTrace()
                Coroutines.main {
                    listener?.onFailure(e.message)

                }

            }

        }
    }

    fun requestInvitesLocal(){

        listener?.onStarted()
        Coroutines.io {
            try {

                val response = repository.getLocalInvites()


                Coroutines.main {

                    listener?.onSuccess(response)

                }
            } catch (e: Exception) {
                e.printStackTrace()
                Coroutines.main {
                    listener?.onFailure(e.message)

                }

            }

        }
    }

    fun updateInvite(invite: InviteResponse.Result) {

        Coroutines.io {

            repository.updateInvitation(invite)
        }

    }
}