package com.example.contacts.dialog

import androidx.lifecycle.ViewModel
import com.example.contacts.Contact


class AddContactViewModel(private val repository: AddContactRepository) : ViewModel() {
    fun add(contact: Contact) {
        repository.add(contact)
    }
}