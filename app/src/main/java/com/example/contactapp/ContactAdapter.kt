package com.example.contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactapp.Interface.OnContactItemClickListener
import com.example.contactapp.model.ContactData

class ContactAdapter(private var contactList: List<ContactData?>?) :
    Adapter<ContactAdapter.ContactViewHolder>() {

        var OnContactClick: OnContactItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contactList?.size ?: 0

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList?.get(position)
        //val contact: ContactItem ?= null
        holder.bind(contact)

        holder.itemView.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                OnContactClick?.onContactItemClick(contactItem = contact, position = position)
            }

        })
    }

    class ContactViewHolder(itemView : View): ViewHolder(itemView ) {

        private val userImage : ImageView = itemView.findViewById(R.id.user_profile_picture_iv)
        private val userName: TextView = itemView.findViewById(R.id.user_name_tv)
        private val userPhoneNumber : TextView = itemView.findViewById(R.id.user_phone_number_tv)

        fun bind(contact: ContactData?) {
            userImage.setImageResource(contact?.image ?: R.drawable.ic_user)
            userName.text = contact?.user?.name ?: "Name Placeholder"
            userPhoneNumber.text = contact?.user?.phoneNumber ?: "01234567890"
        }
    }


}