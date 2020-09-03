package com.example.viewpager2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Product {

    public String id;
    public String food_name;
    public  String price;
    public  String resturant_name;
    public String rating;
    public  String image;

    public Product() {
    }
    public Field[] getAllFields(){
        return this.getClass().getDeclaredFields() ;
    }

    public Product(String id, String food_name, String price, String resturant_name, String rating, String image) {
        this.id = id;
        this.food_name = food_name;
        this.price = price;
        this.resturant_name = resturant_name;
        this.rating = rating;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getResturant_name() {
        return resturant_name;
    }

    public void setResturant_name(String resturant_name) {
        this.resturant_name = resturant_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
