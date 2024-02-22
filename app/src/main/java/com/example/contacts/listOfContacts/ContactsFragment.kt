package com.example.contacts.listOfContacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.ContactsAdapter
import com.example.contacts.DataBase
import com.example.contacts.dialog.DialogFragment
import com.example.contacts.MainActivity
import com.example.contacts.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private val dataBase: DataBase = DataBase.getInstance()
    private var contactsAdapter: ContactsAdapter? = null
    private lateinit var viewModel: ListViewModel

//    private val requestPermission =
//        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
//            createContact()
//            createContact()
//        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = ListRepository(dataBase)
        viewModel = ViewModelProvider(this, ListFactory(repository))[ListViewModel::class.java]
        viewModel.getList()
        checkList()
        contactsAdapter = ContactsAdapter()
        binding.rvContacts.adapter = contactsAdapter
//        requestPermission.launch(arrayOf(READ_CONTACTS, WRITE_CONTACTS))
        binding.buttonAddNote.setOnClickListener {
            val dialog = DialogFragment()
            dialog.show((activity as MainActivity).supportFragmentManager,"dialog")
        }
    }

    override fun onResume() {
        super.onResume()
        showContacts()

    }
    private fun showContacts(){
        contactsAdapter?.setListOfContacts(viewModel.getList())
    }

    override fun onDestroyView() {
        contactsAdapter = null
        _binding = null
        super.onDestroyView()
    }
    private fun checkList(){
        if (viewModel.getList().isEmpty()){
            binding.tvAddContacts.visibility = View.VISIBLE
            binding.rvContacts.visibility = View.INVISIBLE
            binding.tvContacts.visibility = View.INVISIBLE
        }
    }
//    private fun createContact() {
//        val contentResolver: ContentResolver = requireContext().contentResolver
//
//        val operations = ArrayList<ContentProviderOperation>()
//        operations.add(
//            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
//                .build()
//        )
//        operations.add(
//            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(
//                    ContactsContract.Data.MIMETYPE,
//                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
//                )
//                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, "User1")
//                .build()
//        )
//        operations.add(
//            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(
//                    ContactsContract.Data.MIMETYPE,
//                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
//                )
//                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "0501231223")
//                .withValue(
//                    ContactsContract.CommonDataKinds.Phone.TYPE,
//                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
//                )
//                .build()
//        )
//        try {
//            contentResolver.applyBatch(ContactsContract.AUTHORITY, operations)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
}