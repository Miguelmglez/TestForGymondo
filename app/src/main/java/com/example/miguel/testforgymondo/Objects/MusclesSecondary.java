package com.example.miguel.testforgymondo.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusclesSecondary {

    private int id;

    private String name;
    @SerializedName("is_front")
    @Expose
    private boolean isFront;


    public MusclesSecondary() {
    }

    public MusclesSecondary(int id, String name, boolean isFront) {
        super();
        this.id = id;
        this.name = name;
        this.isFront = isFront;
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

    public boolean isIsFront() {
        return isFront;
    }

    public void setIsFront(boolean isFront) {
        this.isFront = isFront;
    }

}