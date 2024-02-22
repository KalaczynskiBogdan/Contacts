package com.example.contacts.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddContactFactory (private val repository: AddContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}