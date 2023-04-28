package com.youth4work.HSSC_2023.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.youth4work.HSSC_2023.R
import com.youth4work.HSSC_2023.network.model.response.Message
import com.youth4work.HSSC_2023.ui.public_profile.ProfileActivity
import com.youth4work.HSSC_2023.ui.workmail.ConversationActivity
import com.youth4work.prepapp.network.model.response.Youth


class ChatUserListingAdapter(val mContext:Context,val youthList:ArrayList<Youth>): RecyclerView.Adapter<ChatUserListingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(youth: Youth, mContext: Context) {
            val txtUserFullName=itemView.findViewById<TextView>(R.id.txt_user_full_name)
            val txtChatNow=itemView.findViewById<TextView>(R.id.txt_chat_now)
            val cardUser=itemView.findViewById<CardView>(R.id.card_user)
            val imgUser=itemView.findViewById<ImageView>(R.id.img_user)
            txtUserFullName.text=youth.name
            Picasso.with(mContext).load(youth.imageURL).into(imgUser)
            txtChatNow.setOnClickListener {
                var intent = Intent(mContext, ConversationActivity::class.java)
                val msg = Message()
                msg.setSenderUserId(youth.userId.toString())
                val gson = Gson()
                val m: String = gson.toJson(msg, Message::class.java)
                intent.putExtra("msg", m)
                mContext.startActivity(intent)
            }
            cardUser.setOnClickListener {
                ProfileActivity.show(mContext,youth.name,youth.imageURL,youth.userId)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.single_user_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(youthList.get(position),mContext)
    }

    override fun getItemCount(): Int {
        return youthList.size
    }
}