package com.example.contactsapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactsapplication.databinding.ActivityHomeBinding
import com.example.contactsapplication.model.Contact

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    lateinit var name: String
    lateinit var phone: String
    lateinit var description: String
    val contactsList = mutableListOf<Contact>()
    var adapter = ContactsAdapter(contactsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSaveBtnClick()
        initRV()
    }

    private fun initRV() {
        binding.rvContacts.adapter = adapter
        adapter.onItemClicked = object: ContactsAdapter.OnItemClicked{
            override fun onClick(contact: Contact) {
                navigateToContactDetailsActivity(contact)
            }
        }
    }

    private fun navigateToContactDetailsActivity(contact: Contact) {
        val intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra("contact", contact)
        startActivity(intent)
    }

    private fun onSaveBtnClick() {
        binding.btnSave.setOnClickListener{
            if (!validateTextFields()){
                return@setOnClickListener
            }

            name = binding.edtName.text?.trim().toString()
            phone = binding.edtPhoneNumber.text?.trim().toString()
            description = binding.edtDescription.text?.trim().toString()

            val contact = Contact(name, description = description, phone = phone)
            contactsList.add(contact)
            adapter.notifyItemInserted(contactsList.size-1)
        }
    }

    private fun validateTextFields(): Boolean {
        name = binding.edtName.text?.trim().toString()
        phone = binding.edtPhoneNumber.text?.trim().toString()
        binding.containerName.error = validateName(name)
        binding.containerPhoneNumber.error = validatePhoneNumber(phone)

        return validateName(name) == null && validatePhoneNumber(phone) == phone

    }

    private fun validateName(name: String): String? {
        if (name.isEmpty()){
            return "Required"
        }

        if(name.length < 3){
            return "Name can't be less than three characters!"
        }

        val namePattern = "^[a-zA-Z]+$"
        if (!name.matches(namePattern.toRegex())){
            return "Name can only contain letters"
        }

        return null
    }

    private fun validatePhoneNumber(phone: String): String? {
        if (phone.isEmpty()){
            return "Required"
        }

        if (phone.length < 11){
            return "Phone number can't be less than 11 digits"
        }

        val phonePattern = "^[0-9]+$"
        if (!phone.matches(phonePattern.toRegex())){
            return "Phone number can only contain digits"
        }

        return null
    }
}