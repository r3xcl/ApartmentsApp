package com.example.myapplication.addimages;

public class Image {

    String ImageName;
    String ImageUrl;

    public Image() {
    }

    public Image(String imageName, String imageUrl) {
        ImageName = imageName;
        ImageUrl = imageUrl;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
