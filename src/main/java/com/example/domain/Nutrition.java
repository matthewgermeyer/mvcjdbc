package com.example.domain;

public class Nutrition {
    private long id;
    private String product;
    private int calories;
    private int carbs;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public int getCarbs() {
        return carbs;
    }
    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nutrition nutrition = (Nutrition) o;

        if (id != nutrition.id) return false;
        if (calories != nutrition.calories) return false;
        if (carbs != nutrition.carbs) return false;
        return product != null ? product.equals(nutrition.product) : nutrition.product == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + calories;
        result = 31 * result + carbs;
        return result;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", calories=" + calories +
                ", carbs=" + carbs +
                '}';
    }
}
