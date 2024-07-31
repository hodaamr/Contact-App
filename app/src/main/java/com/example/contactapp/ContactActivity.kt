package com.example.contactapp

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.Interface.OnContactItemClickListener
import com.example.contactapp.databinding.ActivityContactsBinding
import com.example.contactapp.model.ContactData
import com.example.contactapp.model.User

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private val contacts = mutableListOf<ContactData>()
    private var contactAdapter: ContactAdapter = ContactAdapter(contacts)

    var count: Int = 0
    private lateinit var name: String
    private lateinit var phoneNumber: String
    private lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        addContactClick()
    }

    private fun addContactClick(){

        binding.saveBtn.setOnClickListener {
            name = binding.nameEditText.text.toString()
            phoneNumber = binding.phoneNumberEditText.text.toString()
            description = binding.phoneNumberEditText.text.toString()
            createContactList(name, phoneNumber, description)
        }
    }

    private fun initRecyclerView() {
        binding.contactRecyclerIew.adapter = contactAdapter
    }

    private fun contactClick(){
        contactAdapter.OnContactClick = object : OnContactItemClickListener {
            override fun onContactItemClick(contactItem: ContactData?, position: Int) {
                val intent = Intent(
                    this@ContactActivity, ActivityContactDetails::class.java
                )
                startActivity(intent)
            }
        }
    }

    private fun createContactList(name: String, phoneNumber: String, description: String) {
            contacts.add(
                ContactData(
                    image = R.drawable.ic_user,
                    user = User(name, phoneNumber, R.drawable.ic_user)
                )
            )
        contactClick()

    }
}