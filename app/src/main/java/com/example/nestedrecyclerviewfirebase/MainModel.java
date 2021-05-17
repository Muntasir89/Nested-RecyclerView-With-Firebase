package com.example.nestedrecyclerviewfirebase;

import java.util.ArrayList;

public class MainModel {
    private String title;
    private ArrayList<ChildModel> ChildDataList;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<ChildModel> getArrayList() {
        return ChildDataList;
    }
    public void setArrayList(ArrayList<ChildModel> arrayList) {
        this.ChildDataList = arrayList;
    }
}
