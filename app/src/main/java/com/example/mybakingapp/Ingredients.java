package com.example.mybakingapp;

public class Ingredients {

    private String quantity;
    private String measure;
    private String ingredient;

    public Ingredients(String quantity, String measure, String ingredient) {

        this.quantity = quantity;
        this.measure = measure;
        this.ingredient=ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String name) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String serving) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
