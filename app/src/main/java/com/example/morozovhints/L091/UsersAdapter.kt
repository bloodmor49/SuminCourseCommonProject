package com.example.morozovhints.L091

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.R

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    var listOfUsers = mutableListOf<L9User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textViewUserName = itemView.findViewById<TextView>(R.id.textViewUserName)
        val textViewUserSurname = itemView.findViewById<TextView>(R.id.textViewUserSurname)
        val textViewUserAge = itemView.findViewById<TextView>(R.id.textViewUserAge)
        val textViewUserGender = itemView.findViewById<TextView>(R.id.textViewUserGender)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            var user = listOfUsers[position]
            textViewUserName.text = user.name
            textViewUserSurname.text = user.surname
            textViewUserAge.text = user.age.toString()
            textViewUserGender.text = user.gender
        }
    }

    override fun getItemCount(): Int = listOfUsers.size
}