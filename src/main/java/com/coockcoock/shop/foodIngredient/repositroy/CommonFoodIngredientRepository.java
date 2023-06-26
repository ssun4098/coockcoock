package com.coockcoock.shop.foodIngredient.repositroy;

import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.foodIngredient.entity.FoodIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonFoodIngredientRepository extends JpaRepository<FoodIngredient, FoodIngredientId> {
}
