package com.example.hw3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MealAdapter(private val meals: List<Meal>, private val onClick: (String) -> Unit) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealNameTextView: TextView = itemView.findViewById(R.id.mealName)
        val mealImageView: ImageView = itemView.findViewById(R.id.mealImage)

        init {
            itemView.setOnClickListener {
                onClick(meals[adapterPosition].idMeal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.mealNameTextView.text = meals[position].strMeal
        Glide.with(holder.itemView.context)
            .load(meals[position].strMealThumb)
            .into(holder.mealImageView)
    }

    override fun getItemCount(): Int = meals.size
}