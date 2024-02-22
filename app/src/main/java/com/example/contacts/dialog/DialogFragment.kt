package com.example.contacts.dialog

import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.Contact
import com.example.contacts.DataBase
import com.example.contacts.MainActivity
import com.example.contacts.R
import com.example.contacts.createContact.CreateContactFragment
import com.example.contacts.databinding.FragmentDialogBinding
import com.example.contacts.listOfContacts.ContactsFragment

class DialogFragment : DialogFragment() {
    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!
    private val dataBase: DataBase = DataBase.getInstance()
    private lateinit var viewModel: AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = AddContactRepository(dataBase)
        viewModel = ViewModelProvider(this, AddContactFactory(repository))[AddContactViewModel::class.java]

        binding.btnDevice.setOnClickListener {
            getContacts()
            val fragment = ContactsFragment()
            (activity as MainActivity).navigateToNextScreen(fragment)
            dismiss()
        }
        binding.btnCreate.setOnClickListener {
            val fragment = CreateContactFragment()
            (activity as MainActivity).navigateToNextScreen(fragment)
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun getContacts() {
        val contentResolver: ContentResolver = requireContext().contentResolver
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cursor = contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {

                val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val nameIndex =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                do {

                    val contactId = it.getString(idIndex)
                    val contactName = it.getString(nameIndex)
                    val contactPhone = it.getString(phoneIndex)

                    val contact = Contact(contactId.toInt(),contactName, contactPhone, R.drawable.ic_man)
                    viewModel.add(contact)
                } while (it.moveToNext())
            }
        }
    }
}