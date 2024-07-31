package com.example.contactapp

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.Interface.OnContactItemClickListener
import com.example.contactapp.databinding.ActivityContactsBinding
import com.example.contactapp.model.ContactData
import com.example.contactapp.model.User

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private val contacts = mutableListOf<ContactData>()
    private var contactAdapter: ContactAdapter = ContactAdapter(contacts)

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

    private fun validateInputs(name: String, phoneNumber: String): Boolean {
        if (name.isEmpty() || name.length < 3) {
            binding.nameEditText.error = "Name must be at least 3 characters long"
            return false
        }

        val phonePattern = Regex("^0\\d{10}$")
        if (!phonePattern.matches(phoneNumber)) {
            binding.phoneNumberEditText.error = "Phone number must start with 0 and be 11 digits long"
            return false
        }

        return true
    }



    private fun addContactClick(){

        binding.saveBtn.setOnClickListener {
            initFields()
            if (validateInputs(name, phoneNumber)){
                createContactList(name, phoneNumber, description)
                contactAdapter.notifyItemInserted(0)
                binding.contactRecyclerIew.scrollToPosition(0)
                Toast.makeText(this,"Contact Added Successfully!", Toast.LENGTH_SHORT).show()
                clearFields()
            }

        }
    }

    private fun initFields(){
        name = binding.nameEditText.text.toString()
        phoneNumber = binding.phoneNumberEditText.text.toString()
        description = binding.descriptionEditText.text.toString()
    }

    private fun clearFields(){
        binding.nameEditText.text = Editable.Factory.getInstance().newEditable("")
        binding.phoneNumberEditText.text = Editable.Factory.getInstance().newEditable("")
        binding.descriptionEditText.text = Editable.Factory.getInstance().newEditable("")

    }

    private fun initRecyclerView() {
        binding.contactRecyclerIew.adapter = contactAdapter
    }

    private fun contactClick(){
        initFields()
        contactAdapter.OnContactClick = object : OnContactItemClickListener {
            override fun onContactItemClick(contactItem: ContactData?, position: Int) {
                val intent = Intent(
                    this@ContactActivity, ActivityContactDetails::class.java
                ).apply {
                    putExtra("contact_name", name)
                    putExtra("contact_phone_number", phoneNumber)
                    putExtra("contact_description", description)
                }
                startActivity(intent)
            }
        }
    }

    private fun createContactList(name: String, phoneNumber: String, description: String) {
        contacts.add(
            0,
                ContactData(
                    image = R.drawable.ic_user,
                    user = User(name, phoneNumber, R.drawable.ic_user)
                )
            )
        contactClick()

    }
}