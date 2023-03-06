package com.clint.cars.features.cars

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clint.cars.R
import com.clint.cars.core.extensions.beautifyDateBasedOnYear
import com.clint.cars.core.extensions.inflate
import com.clint.cars.core.extensions.loadFromUrl
import javax.inject.Inject
import kotlin.properties.Delegates

class CarsAdapter @Inject constructor() : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    internal var carDetailsList: List<CarEntity> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImageView: ImageView = itemView.findViewById(R.id.carImageView)
        val carTitle: TextView = itemView.findViewById(R.id.carTitle)
        val carDate: TextView = itemView.findViewById(R.id.carDate)
        val carSubtitle: TextView = itemView.findViewById(R.id.carSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.row_car))

    override fun getItemCount(): Int = carDetailsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carDetails = carDetailsList[position]
        holder.carImageView.loadFromUrl(carDetails.imageUrl)
        holder.carTitle.text = carDetails.title
        holder.carDate.text = carDetails.date.beautifyDateBasedOnYear()
        holder.carSubtitle.text = carDetails.subtitle
    }
}