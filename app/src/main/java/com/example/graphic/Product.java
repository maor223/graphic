package com.example.graphic;

import android.widget.ImageView;

public class Product {
    private String color, name;
    private ImageView icon;

    public Product(String color, String name, ImageView icon){
        this.color = color;
        this.name = name;
        this.icon = icon;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }
}
