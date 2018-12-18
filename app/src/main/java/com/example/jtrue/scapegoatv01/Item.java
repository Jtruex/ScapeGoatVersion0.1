package com.example.jtrue.scapegoatv01;

public class Item {
    public String name;
    public int jsonId;
    public int gePrice;
    public String iconLink;

    public Item(){

    }

    // Constructor used when name is provided
    public Item(String name){
        this.name = name;
    }

    // Constructor used when name is provided
    public Item(int jsonId){
        this.jsonId = jsonId;
    }

}
