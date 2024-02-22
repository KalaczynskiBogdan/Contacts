package com.example.contacts.createContact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.Contact
import com.example.contacts.DataBase
import com.example.contacts.MainActivity
import com.example.contacts.R
import com.example.contacts.databinding.FragmentCreateContactBinding
import com.example.contacts.dialog.AddContactFactory
import com.example.contacts.dialog.AddContactRepository
import com.example.contacts.dialog.AddContactViewModel
import com.example.contacts.listOfContacts.ContactsFragment

class CreateContactFragment : Fragment() {
    private var _binding: FragmentCreateContactBinding? = null
    private val binding get() = _binding!!
    private val dataBase: DataBase = DataBase.getInstance()
    private lateinit var viewModel: AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = AddContactRepository(dataBase)
        viewModel = ViewModelProvider(this, AddContactFactory(repository))[AddContactViewModel::class.java]
        binding.btnSave.setOnClickListener {
           createContact()
        }
    }
    private fun createContact(){
        val name = binding.etName.text.toString()
        val number = binding.etNumber.text.toString()
        val index = dataBase.getList().size
        val contact = Contact(index, name, number, R.drawable.ic_man)
        viewModel.add(contact)
        val fragment = ContactsFragment()
        (activity as MainActivity).navigateToNextScreen(fragment)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}