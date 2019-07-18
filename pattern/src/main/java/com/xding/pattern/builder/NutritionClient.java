package com.xding.pattern.builder;

/**
 * @author xding
 * @version 0.1 2017/9/20
 */
public class NutritionClient {
    public static void main(String[] args) {
        NutritionFacts nf = new NutritionFacts.Builder(240, 8).setCalories(100).setSodium(35)
                .setCarbohydrate(27).build();
    }
}
