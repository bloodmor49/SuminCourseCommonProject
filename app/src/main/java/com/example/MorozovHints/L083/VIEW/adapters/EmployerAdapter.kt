package com.example.MorozovHints.L083.VIEW.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L083.MODEL.pojo.Employer
import com.example.MorozovHints.R

/**
 * RecyclerView Employer Adapter
 */

class EmployerAdapter : RecyclerView.Adapter<EmployerAdapter.EmployerViewHolder>() {

    var listOfEmployer = mutableListOf<Employer>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class EmployerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
        var textViewLastName: TextView = itemView.findViewById(R.id.textViewLastName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.employer_item, parent, false)
        return EmployerViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployerViewHolder, position: Int) {
        val employer = listOfEmployer[position]
        holder.textViewName.text = employer.fName
        holder.textViewLastName.text = employer.lName
    }

    override fun getItemCount(): Int = listOfEmployer.size


}