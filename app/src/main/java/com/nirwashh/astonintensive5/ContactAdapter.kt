package com.nirwashh.astonintensive5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nirwashh.astonintensive5.databinding.ItemContactBinding
import com.nirwashh.astonintensive5.model.Contact
import kotlin.random.Random
import kotlin.random.nextInt

class ContactAdapter(
    private val listener: OnContactClickListener
) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contactList = mutableListOf<Contact>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) {
        val contact = contactList[position]
        with(holder) {
            with(binding) {
                tvName.text = contact.name
                tvLastname.text = contact.lastName
                tvTelnumber.text = contact.telNumber
                ID.text = contact.id.toString()
                Glide.with(root).load(contact.imageUrl)
                    .centerCrop()
                    .circleCrop()
                    .placeholder(randomPlaceholder())
                    .into(imgUser)
                itemView.setOnClickListener {
                    listener.onContactClick(contact)
                }
                itemView.setOnLongClickListener {
                    listener.onContactLongClick(position)
                    true
                }
            }
        }
    }

    private fun randomPlaceholder(): Int {
        return when (Random.nextInt(1..16)) {
            1 -> R.drawable.users_1
            2 -> R.drawable.users_2
            3 -> R.drawable.users_3
            4 -> R.drawable.users_4
            5 -> R.drawable.users_5
            6 -> R.drawable.users_6
            7 -> R.drawable.users_7
            8 -> R.drawable.users_8
            9 -> R.drawable.users_9
            10 -> R.drawable.users_10
            11 -> R.drawable.users_11
            12 -> R.drawable.users_12
            13 -> R.drawable.users_13
            14 -> R.drawable.users_14
            15 -> R.drawable.users_15
            16 -> R.drawable.users_16
            else -> {
                R.drawable.users_16
            }
        }
    }

    fun updateList(listItems: List<Contact>) {
        val diffCallback = ContactDiffCallback(contactList, listItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        contactList.clear()
        contactList.addAll(listItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun removeItem(position: Int) {
        contactList.removeAt(position)
        notifyItemRangeChanged(0, contactList.size)
        notifyItemRemoved(position)

    }

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)
}

interface OnContactClickListener {
    fun onContactClick(contact: Contact)
    fun onContactLongClick(position: Int)
}

class ContactDiffCallback(
    private val oldList: List<Contact>,
    private val newList: List<Contact>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}