package com.example.contactsapplication

import android.content.Context
import android.provider.ContactsContract.RawContacts
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.databinding.ActivityItemContactBinding
import com.example.contactsapplication.model.Contact
//import com.example.contactsapplication.databinding.ItemContactBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.newCoroutineContext

class ContactsAdapter(val contactsList: MutableList<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(val itemContactBinding: ActivityItemContactBinding): RecyclerView.ViewHolder(itemContactBinding.root){
        fun bind(contact: Contact){
            itemContactBinding.tvName.text = contact.name
            itemContactBinding.tvPhone.text = contact.phone
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = ActivityItemContactBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contactsList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val contact = contactsList[p1]
        p0.bind(contact)
        p0.itemContactBinding.root.setOnClickListener {
            onItemClicked?.onClick(contact)
        }
    }

    interface OnItemClicked{
        fun onClick(contact: Contact)
    }

    var onItemClicked: OnItemClicked? = null
}




