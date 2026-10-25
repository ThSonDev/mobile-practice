package com.example.lab2_6;

public class Dish {
    private String name;
    private Thumbnail thumbnail;
    private boolean promotion;

    public Dish(String name, Thumbnail thumbnail, boolean promotion) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public boolean isPromotion() {
        return promotion;
    }
}
