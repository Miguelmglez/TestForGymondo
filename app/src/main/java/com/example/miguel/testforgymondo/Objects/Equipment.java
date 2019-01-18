package com.example.miguel.testforgymondo.Objects;

public class Equipment {


    private int id;

    private String name;


    public Equipment() {
    }

    public Equipment(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

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

}