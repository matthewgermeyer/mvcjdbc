package com.example.domain;

import com.example.common.FoodType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Nutrition {
    private long id;

    @NotNull
    @Size(min=2, max = 50)
    private String product;

    @NotNull
    @Min(0)
    private int calories;

    @NotNull
    @Min(0)
    private int carbs;

    private boolean clean;

    @NotNull
    private FoodType foodType;

    @NotNull
    private int productId;



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

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nutrition nutrition = (Nutrition) o;

        if (id != nutrition.id) return false;
        if (calories != nutrition.calories) return false;
        if (carbs != nutrition.carbs) return false;
        if (clean != nutrition.clean) return false;
        if (productId != nutrition.productId) return false;
        if (product != null ? !product.equals(nutrition.product) : nutrition.product != null) return false;
        return foodType == nutrition.foodType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + calories;
        result = 31 * result + carbs;
        result = 31 * result + (clean ? 1 : 0);
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + productId;
        return result;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", calories=" + calories +
                ", carbs=" + carbs +
                ", clean=" + clean +
                ", foodType=" + foodType +
                ", productId=" + productId +
                '}';
    }
}
