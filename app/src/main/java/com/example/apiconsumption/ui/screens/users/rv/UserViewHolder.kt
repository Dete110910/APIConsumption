package com.example.apiconsumption.ui.screens.users.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.databinding.UserViewBinding
import com.example.apiconsumption.utils.loadSquareImage

class UserViewHolder(
    private val onBanksClickListener: (user: User) -> Unit,
    private val onBeersClickListener: (user: User) -> Unit,
    private val binding: UserViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding){
            btnBanks.setOnClickListener{
                onBanksClickListener(user)
            }

            btnBeers.setOnClickListener{
                onBeersClickListener(user)
            }
            tvUserId.text = user.id.toString()
            tvUserFullName.text = "${user.firstName} ${user.lastName}"
            tvUserGender.text = user.gender
            ivAvatar.loadSquareImage(user.avatar)
        }
    }
}