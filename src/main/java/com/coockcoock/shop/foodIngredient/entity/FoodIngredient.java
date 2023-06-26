package com.coockcoock.shop.foodIngredient.entity;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.recipe.entity.Recipe;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(FoodIngredientId.class)
@Table(name = "recipe_ingredient")
@Entity
public class FoodIngredient {
    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    Recipe recipe;
    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @Column(name = "amount")
    String amount;
}
