package com.example.contacts.listOfContacts

import com.example.contacts.Contact
import com.example.contacts.DataBase

class ListRepository(private val dataBase: DataBase) {
    fun getList(): ArrayList<Contact>{
        return dataBase.getList()
    }
}