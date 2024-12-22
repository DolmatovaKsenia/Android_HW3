package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailFragment : Fragment() {

    private lateinit var mealImageView: ImageView
    private lateinit var mealNameTextView: TextView
    private lateinit var mealInstructionsTextView: TextView
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_detail, container, false)

        mealImageView = view.findViewById(R.id.mealImage)
        mealNameTextView = view.findViewById(R.id.mealName)
        mealInstructionsTextView = view.findViewById(R.id.mealInstructions)
        backButton = view.findViewById(R.id.backButton)

        val mealId = arguments?.getString("MEAL_ID") ?: return view

        loadMealDetails(mealId)

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    private fun loadMealDetails(mealId: String) {
        RetrofitClient.instance.getMealById(mealId).enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val meal = response.body()!!.meals[0]
                    mealNameTextView.text = meal.strMeal
                    mealInstructionsTextView.text = meal.strInstructions

                    Glide.with(this@MealDetailFragment)
                        .load(meal.strMealThumb)
                        .into(mealImageView)
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(context, "Error on loading!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}