I have the following user profile information:

- First Name: {firstName}
- Last Name: {lastName}
- Date of Birth: {dateOfBirth}
- Gender: {gender}
- Height: {height} cm
- Weight: {weight} kg
- Dietary Preferences: {dietaryPreferences}
- Goals: {goals}

Based on this information, please generate a detailed 7-day meal plan. The meal plan should include:

- **Date** for each day
- **Total Calories** per day
- **Meals** for each day, categorized as Breakfast, Lunch, Dinner, and Snacks

For each meal, provide:
- **Meal Type** (Breakfast, Lunch, Dinner, Snack)
- **Description** (Recipe name or brief description)
- **Calories**
- **Protein** (in grams)
- **Fat** (in grams)
- **Carbohydrates** (in grams)
- **Instructions** (Cooking instructions)
- **Image URL** (optional)

Please format the response in JSON with the following structure:

```json
{
  "mealPlans": [
    {
      "date": "YYYY-MM-DD",
      "totalCalories": 2000,
      "meals": [
        {
          "mealType": "Breakfast",
          "description": "Oatmeal with Fruits",
          "calories": 350,
          "protein": 10,
          "fat": 5,
          "carbs": 60,
          "instructions": "Cook oats with milk and add sliced fruits on top.",
          "imageUrl": "http://example.com/oatmeal.jpg"
        },
        {
          "mealType": "Lunch",
          "description": "Grilled Chicken Salad",
          "calories": 500,
          "protein": 40,
          "fat": 20,
          "carbs": 30,
          "instructions": "Grill chicken breast and mix with fresh vegetables and dressing.",
          "imageUrl": "http://example.com/chicken_salad.jpg"
        },
        // More meals...
      ]
    },
    // More days...
  ],
  "shoppingList": [
    "Oats",
    "Milk",
    "Fruits",
    "Chicken Breast",
    "Mixed Vegetables",
    "Salad Dressing",
    // More items...
  ]
}
