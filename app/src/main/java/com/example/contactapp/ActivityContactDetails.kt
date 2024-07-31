package com.example.contactapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.databinding.ActivityContactDetailsBinding
import com.example.contactapp.databinding.ActivityContactsBinding

class ActivityContactDetails : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contactNameTv.text = intent.getStringExtra("contact_name")
        binding.userPhoneNumberTv.text = intent.getStringExtra("contact_phone_number")
        binding.userDescriptionTv.text = intent.getStringExtra("contact_description")
        binding.avatarIv.setImageResource(R.drawable.boy)
    }
}