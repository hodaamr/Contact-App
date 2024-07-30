package com.example.contactapp.Interface

import com.example.contactapp.model.ContactData

interface OnContactItemClickListener {
    fun onContactItemClick(contactItem: ContactData?, position: Int)
}