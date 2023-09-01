package com.example.apitask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList : List<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>(){

    // ONCLICK EVENT
    private lateinit var mListener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position : Int)
    }
    fun setOnClickListener(listener : onItemClickListener) {
        mListener = listener
    }


    // DEFAULT GENERATED METHODS
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent,false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.txtUser.text = currentItem.Name + " " + currentItem.Surname;
        holder.txtAmount.text = currentItem.Amount
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    // MY_VIEW HOLDER
    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val txtUser : TextView = itemView.findViewById(R.id.txtUser)
        val txtAmount : TextView = itemView.findViewById(R.id.txtAmount)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}