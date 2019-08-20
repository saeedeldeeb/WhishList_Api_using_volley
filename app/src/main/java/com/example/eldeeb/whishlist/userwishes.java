package com.example.eldeeb.whishlist;

public class userwishes {

    private String wishName;
    private double wishPrice;
    private String wishImage;

    public userwishes(String wishName, double wishPrice, String wishImage) {
        this.wishName = wishName;
        this.wishPrice = wishPrice;
        this.wishImage = wishImage;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    public String getWishImage() {
        return wishImage;
    }

    public void setWishImage(String wishImage) {
        this.wishImage = wishImage;
    }
}
