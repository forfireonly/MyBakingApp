package com.example.mybakingapp;

public class NameServing {

    private String name;
    private String serving;

    public NameServing(String nameBakingItem, String servings) {

        this.name = nameBakingItem;
        this.serving = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }
}
