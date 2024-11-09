Generate a 3-day meal plan with breakfast, lunch, and dinner for each day. Each meal should include:

- Meal type (e.g., "Breakfast", "Lunch", "Dinner")
- Name (e.g., "Salad with egg")
- Calories (e.g., "200kcal")
- Weight in grams (e.g., "150g")
- Nutrient breakdown: protein, carbohydrates, fats (e.g., "Protein: 50g", "Carbohydrates: 40g", "Fats: 10g")

Format the response as a JSON object with the following structure:

{
  "mealPlan": [
    {
      "day": "Day 01",
      "meals": [
        {
          "type": "Breakfast",
          "name": "Salad with egg",
          "calories": "200kcal",
          "weight": "150g",
          "nutrients": {
            "protein": "50g",
            "carbohydrates": "40g",
            "fats": "10g"
          }
        },
        {
          "type": "Lunch",
          "name": "Grilled chicken breast with quinoa",
          "calories": "500kcal",
          "weight": "350g",
          "nutrients": {
            "protein": "80g",
            "carbohydrates": "60g",
            "fats": "30g"
          }
        },
        {
          "type": "Dinner",
          "name": "Baked salmon with broccoli",
          "calories": "600kcal",
          "weight": "250g",
          "nutrients": {
            "protein": "60g",
            "carbohydrates": "20g",
            "fats": "50g"
          }
        }
      ]
    },
    {
      "day": "Day 02",
      "meals": [ ... ]
    },
    {
      "day": "Day 03",
      "meals": [ ... ]
    }
  ]
}

Only return the response in the JSON format specified, with no additional text or explanations.
