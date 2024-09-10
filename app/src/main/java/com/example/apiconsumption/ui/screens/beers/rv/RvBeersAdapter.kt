package com.example.apiconsumption.ui.screens.beers.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.data.api.models.Beer
import com.example.apiconsumption.databinding.BeerViewBinding

class RvBeersAdapter() : RecyclerView.Adapter<BeersViewHolder>() {

    var beerList = emptyList<Beer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        val binding = BeerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BeersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        holder.bind(beerList[position])
    }

    override fun getItemCount(): Int = beerList.size

}