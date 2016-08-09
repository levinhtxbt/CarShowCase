package net.levinh.android_carshowcase.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by levinh on 07/08/2016.
 */
public class Car {
    private String name;
    private String brand;
    @SerializedName("imageFile")
    private String imageUrl;
    private String color;

    public Car(String name, String brand, String imageUrl, String color) {
        this.name = name;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
