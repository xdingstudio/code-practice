package com.xding.pattern.builder;

/**
 * @author xding
 * @version 0.1 2017/9/20
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private int servingSize;
        private int servings;
        // Optional parameters - initialized to default values
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder setCalories(int val) {
            calories = val;
            return this;
        }

        public  Builder setFat(int val) {
            fat = val;
            return this;
        }

        public Builder setSodium(int val) {
            sodium = val;
            return this;
        }

        public Builder setCarbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

}
