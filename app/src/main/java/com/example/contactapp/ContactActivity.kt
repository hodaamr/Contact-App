package com.example.contactapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.Interface.OnContactItemClickListener
import com.example.contactapp.databinding.ActivityContactsBinding
import com.example.contactapp.model.ContactData
import com.example.contactapp.model.User

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private val contacts = mutableListOf<ContactData>()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        createContactList()
        contactAdapter = ContactAdapter(contacts)
        contactAdapter.OnContactClick = object : OnContactItemClickListener {
            override fun onContactItemClick(contactItem: ContactData?, position: Int) {
                val intent = Intent(
                    this@ContactActivity, ActivityContactDetails::class.java
                )
                startActivity(intent)
            }

        }
        binding.contactRecyclerIew.adapter = contactAdapter
    }

    private fun createContactList() {
        for (i in 1..10){
            contacts.add(
                ContactData(
                    image = R.drawable.ic_user,
                    user = User("Hoda", "01112413768", R.drawable.ic_user)
                )
            )
        }

    }
}