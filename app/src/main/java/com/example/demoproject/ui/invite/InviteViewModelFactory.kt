package com.example.demoproject.ui.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject.repo.InviteRepository

class InviteViewModelFactory(private val repository: InviteRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InviteViewModel(repository) as T
    }
}
