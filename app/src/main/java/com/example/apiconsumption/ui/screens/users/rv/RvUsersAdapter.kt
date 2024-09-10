package com.example.apiconsumption.ui.screens.users.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.databinding.UserViewBinding

class RvUsersAdapter(
    private val onBanksClickListener: (user: User) -> Unit,
    private val onBeersClickListener: (user: User) -> Unit
) : RecyclerView.Adapter<UserViewHolder>(){

    var users = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(
            binding = binding,
            onBanksClickListener = onBanksClickListener,
            onBeersClickListener = onBeersClickListener
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }
}