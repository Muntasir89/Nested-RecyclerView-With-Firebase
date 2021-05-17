package com.example.nestedrecyclerviewfirebase;

import java.util.List;

public interface FirebaseLoadListener {
    void onFirebaseLoadSuccess(List<MainModel> MainList);
    void onFirebaseLoadFailed(String Message);
}
