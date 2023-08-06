package com.example.expensemanager.Models;

public class Category {
    private String categoryName;
    private int categoryImage;

    private int CategoryColor;

    public int getCategoryColor() {
        return CategoryColor;
    }

    public void setCategoryColor(int categoryColor) {
        CategoryColor = categoryColor;
    }

    public Category(String categoryName, int categoryImage, int categoryColor) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        CategoryColor = categoryColor;
    }

    public Category(){
    }

    public Category(String categoryName, int categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }
}
