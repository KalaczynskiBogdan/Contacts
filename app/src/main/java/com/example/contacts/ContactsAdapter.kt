package com.example.contacts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(){
    private var listOfContacts: ArrayList<Contact> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setListOfContacts(listOfContacts: ArrayList<Contact>) {
        this.listOfContacts = listOfContacts
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_contact, parent, false
        )
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ContactsViewHolder, position: Int) {
        val contact = listOfContacts[position]
        val text = contact.name
        viewHolder.textViewContact.text = text
        viewHolder.image.setImageResource(contact.image)
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewContact: TextView = itemView.findViewById(R.id.tvContact)
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}