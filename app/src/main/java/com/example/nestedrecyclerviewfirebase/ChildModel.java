package com.example.nestedrecyclerviewfirebase;

public class ChildModel {
    String imgUrl;
    String Name;

    public ChildModel(String imgUrl, String name) {
        this.imgUrl = imgUrl;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
