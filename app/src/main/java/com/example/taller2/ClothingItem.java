package com.example.taller2;

public class ClothingItem {
    private String name;
    private String price;
    private String description;
    private int imageResourceId;

    public ClothingItem(String name, String price,String description, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {

        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {

        return description;
    }

    public int getImageResourceId() {

        return imageResourceId;
    }
}