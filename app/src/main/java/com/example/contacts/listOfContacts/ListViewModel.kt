package com.example.contacts.listOfContacts

import androidx.lifecycle.ViewModel
import com.example.contacts.Contact

class ListViewModel(private val repository: ListRepository) :ViewModel(){
    fun getList():ArrayList<Contact>{
        return repository.getList()
    }
}