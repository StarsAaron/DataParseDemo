package com.aaron.dataparsedemo.xml;

import java.io.Serializable;

/**
 * Created by Aaron on 2017/3/23.
 */

public class Book implements Serializable{
    private int id;
    private String name;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BooK>ID:"+id+"Name:"+name+"Price:"+price;
    }
}
