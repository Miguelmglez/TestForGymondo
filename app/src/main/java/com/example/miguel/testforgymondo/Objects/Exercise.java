package com.example.miguel.testforgymondo.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Exercise {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("license_author")
    @Expose
    private String licenseAuthor;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_original")
    @Expose
    private String nameOriginal;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("license")
    @Expose
    private int license;
    @SerializedName("category")
    @Expose
    private int category;
    @SerializedName("language")
    @Expose
    private int language;
    @SerializedName("muscles")
    @Expose
    private List<Integer> muscles = null;
    @SerializedName("muscles_secondary")
    @Expose
    private List<Integer> musclesSecondary = null;
    @SerializedName("equipment")
    @Expose
    private List<Integer> equipment = null;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public List<Integer> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<Integer> muscles) {
        this.muscles = muscles;
    }

    public List<Integer> getMusclesSecondary() {
        return musclesSecondary;
    }

    public void setMusclesSecondary(List<Integer> musclesSecondary) {
        this.musclesSecondary = musclesSecondary;
    }

    public List<Integer> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Integer> equipment) {
        this.equipment = equipment;
    }

    public Exercise() {
        this.id = 0;
        this.licenseAuthor ="";
        this.status = "";
        this.description = "";
        this.name = "";
        this.nameOriginal = "";
        this.creationDate = "";
        this.uuid = "";
        this.license = 0;
        this.category = 0;
        this.language = 0 ;
        this.muscles = new ArrayList<>();
        this.musclesSecondary = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }
}