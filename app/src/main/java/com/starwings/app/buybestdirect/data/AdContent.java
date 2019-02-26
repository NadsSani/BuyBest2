package com.starwings.app.buybestdirect.data;

import java.io.Serializable;
import java.util.ArrayList;

public class AdContent implements Serializable {
    private int slNo;
    private String adTitle;
    private String adDescription;
    private String adCategory;
    private String adSubCategory;
    private String adClient;
    private String adResources;
    private String adReleaseDate;
    private String adExpiry;
    private String adAmount;
    private String adStatus;
    private String adFeaturedImage;
    private String categoryName;
    private String subCategoryName;
    private String nameOfClient;
    private String email;
    private String phoneNo;
    private String clientDisplayName;

    private ArrayList<ImageResource> AdImageResources;
    private ArrayList<VideoResource> AdVideoResources;

    public AdContent()
    {
        AdImageResources=new ArrayList<ImageResource>();
        AdVideoResources=new ArrayList<VideoResource>();
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdDescription() {
        return adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public String getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }

    public String getAdSubCategory() {
        return adSubCategory;
    }

    public void setAdSubCategory(String adSubCategory) {
        this.adSubCategory = adSubCategory;
    }

    public String getAdClient() {
        return adClient;
    }

    public void setAdClient(String adClient) {
        this.adClient = adClient;
    }

    public String getAdReleaseDate() {
        return adReleaseDate;
    }

    public void setAdReleaseDate(String adReleaseDate) {
        this.adReleaseDate = adReleaseDate;
    }

    public String getAdExpiry() {
        return adExpiry;
    }

    public void setAdExpiry(String adExpiry) {
        this.adExpiry = adExpiry;
    }

    public String getAdAmount() {
        return adAmount;
    }

    public void setAdAmount(String adAmount) {
        this.adAmount = adAmount;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    public ArrayList<ImageResource> getAdImageResources() {
        return AdImageResources;
    }

    public void setAdImageResources(ArrayList<ImageResource> adImageResources) {
        AdImageResources = adImageResources;
    }

    public ArrayList<VideoResource> getAdVideoResources() {
        return AdVideoResources;
    }

    public void setAdVideoResources(ArrayList<VideoResource> adVideoResources) {
        AdVideoResources = adVideoResources;
    }


    public String getAdFeaturedImage() {
        return adFeaturedImage;
    }

    public void setAdFeaturedImage(String adFeaturedImage) {
        this.adFeaturedImage = adFeaturedImage;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getAdResources() {
        return adResources;
    }

    public void setAdResources(String adResources) {
        this.adResources = adResources;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getClientDisplayName() {
        return clientDisplayName;
    }

    public void setClientDisplayName(String clientDisplayName) {
        this.clientDisplayName = clientDisplayName;
    }
}
