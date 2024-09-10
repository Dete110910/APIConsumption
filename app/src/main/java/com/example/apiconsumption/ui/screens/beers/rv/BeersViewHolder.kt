package com.example.apiconsumption.ui.screens.beers.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.R
import com.example.apiconsumption.data.api.models.Beer
import com.example.apiconsumption.databinding.BeerViewBinding

class BeersViewHolder(
    private val binding: BeerViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(beer: Beer) {
        with(binding){
            tvBeerBrand.text = beer.brand
            tvBeerName.text = beer.name
            tvBeerAlcohol.text = tvBeerAlcohol.context.getString(R.string.beer_alcohol, beer.alcohol)
        }
    }
}