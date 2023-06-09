package com.youth4work.HSSC_2023.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

public class Category {
    @SerializedName("attempts")
    private int Attempts;
    @SerializedName("category")
    private String Category;
    @SerializedName("catid")
    private int Catid;
    @SerializedName("parentCategory")
    private String ParentCategory;
    @SerializedName("startDate")
    private String mStartDate;
    @SerializedName("subCategoryImg")
    private String SubCategoryImg;
    @SerializedName("subCatImg")
    private String SubCatImg;
    @SerializedName("subDescription")
    private String SubDescription;
    @SerializedName("questions")
    private String Questions;
    @SerializedName("aspirants")
    private String Aspirants;

    public Category(int attempts, String category, int catid, String parentCategory,String subCategoryImg, String subCatImg, String subDescription, String questions, String aspirants) {
        Attempts = attempts;
        Category = category;
        Catid = catid;
        ParentCategory = parentCategory;
        SubCategoryImg = subCategoryImg;
        SubCatImg = subCatImg;
        SubDescription = subDescription;
        Questions = questions;
        Aspirants = aspirants;
    }


    public String getSubCatImg() {
        return SubCatImg;
    }

    public void setSubCatImg(String subCatImg) {
        SubCatImg = subCatImg;
    }

    public Category(int selectedCatID) {
        this.Catid = selectedCatID;
    }
    public Category(int selectedCatID,String CatName) {
        this.Catid = selectedCatID;
        this.Category=CatName;
    }
    public int getAttempts() {
        return Attempts;
    }

    public void setAttempts(int Attempts) {
        this.Attempts = Attempts;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getCatid() {
        return Catid;
    }

    public void setCatid(int Catid) {
        this.Catid = Catid;
    }

    public String getParentCategory() {
        return ParentCategory;
    }

    public void setParentCategory(String ParentCategory) {
        this.ParentCategory = ParentCategory;
    }

    public String getStartDate() {
        if (mStartDate != null ) {
            mStartDate = mStartDate.replace("/Date(", "");
            mStartDate = mStartDate.replace("+0530)/", "");
        } else {
            mStartDate = String.valueOf(new Date().getTime());
        }

        return mStartDate;
    }

    public Date getStartDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(getStartDate()));
        return calendar.getTime();
    }

    public String getSubCategoryImg() {
        return SubCategoryImg;
    }

    public void setSubCategoryImg(String subCategoryImg) {
        SubCategoryImg = subCategoryImg;
    }

    public String getSubDescription() {
        return SubDescription;
    }

    public void setSubDescription(String subDescription) {
        SubDescription = subDescription;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public String getAspirants() {
        return Aspirants;
    }

    public void setAspirants(String aspirants) {
        Aspirants = aspirants;
    }

    public void setStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }
}