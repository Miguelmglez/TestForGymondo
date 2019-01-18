package com.example.miguel.testforgymondo.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseImage {


    private int id;
    @SerializedName("license_author")
    @Expose
    private String licenseAuthor;

    private String status;

    private String image;
    @SerializedName("is_main")
    @Expose
    private boolean isMain;

    private int license;

    private int exercise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseAuthor() {
        return licenseAuthor;
    }

    public void setLicenseAuthor(String licenseAuthor) {
        this.licenseAuthor = licenseAuthor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIsMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public int getExercise() {
        return exercise;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }

}
