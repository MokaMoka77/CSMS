package com.csms.modal;

public class IngredientPerDay {
    private int weight;
    private Ingredient nl;

    public IngredientPerDay() {
    }

    public IngredientPerDay(int weight, Ingredient nl) {
        this.weight = weight;
        this.nl = nl;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Ingredient getNl() {
        return nl;
    }

    public void setNl(Ingredient nl) {
        this.nl = nl;
    }

    @Override
    public String toString() {
        return weight+"";
    }
}
