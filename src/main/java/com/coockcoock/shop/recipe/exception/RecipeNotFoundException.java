package com.coockcoock.shop.recipe.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String message) {
        super("NotFound Recipe ID: " + message);
    }
}
