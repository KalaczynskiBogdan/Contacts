package com.example.contacts.dialog

import com.example.contacts.Contact
import com.example.contacts.DataBase

class AddContactRepository(private val dataBase: DataBase) {
    fun add(contact: Contact){
        dataBase.add(contact)
    }
}