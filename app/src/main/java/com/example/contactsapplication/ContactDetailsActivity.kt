package com.example.contactsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.contactsapplication.databinding.ActivityContactDetailsBinding
import com.example.contactsapplication.databinding.ActivityItemContactBinding
import com.example.contactsapplication.model.Contact
import androidx.core.content.IntentCompat.getParcelableExtra as getParcelableExtra1

class ContactDetailsActivity: AppCompatActivity() {
    lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActivityViews()

    }

    private fun setActivityViews(){
        val contact = getParcelableExtra1(intent, "contact", Contact::class.java)
        contact?.let {
            contact -> binding.tvNameContent.text = contact.name
                       binding.tvPhoneContent.text = contact.phone
                       binding.tvDescriptionContent.text = contact.description
        }
    }
}