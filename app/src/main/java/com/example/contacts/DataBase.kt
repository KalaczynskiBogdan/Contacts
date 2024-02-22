package com.example.contacts

class DataBase {

    private val listOfContacts : ArrayList<Contact> = ArrayList()

    companion object {
        private var instance: DataBase? = null
        fun getInstance(): DataBase {
            if (instance == null) {
                instance = DataBase()
            }
            return instance as DataBase
        }
    }

    fun getList(): ArrayList<Contact>{
        return ArrayList(listOfContacts)
    }
    fun add(contact: Contact) {
        listOfContacts.add(contact)
    }
}