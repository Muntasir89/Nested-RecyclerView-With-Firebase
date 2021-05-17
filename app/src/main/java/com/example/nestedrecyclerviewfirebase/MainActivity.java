package com.example.nestedrecyclerviewfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FirebaseLoadListener {
    FirebaseLoadListener firebaseLoadListener;
    RecyclerView RVMain;
    DatabaseReference myData;
    MainAdapter MAd;
    ArrayList<MainModel> MainDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Init
        myData = FirebaseDatabase.getInstance().getReference("MyData");//Database Project 'Firebase'
        firebaseLoadListener = this;
        //Load Data
        getFirebaseData();
        RVMain = findViewById(R.id.RVMain);
        RVMain.setHasFixedSize(true);
        RVMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MAd = new MainAdapter(this, MainDataList);
        RVMain.setAdapter(MAd);
    }

    private void getFirebaseData() {
        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<MainModel> MainModelList = new ArrayList<>();
                for(DataSnapshot childSnapshot:dataSnapshot.getChildren()){
                    MainModel mainModel = new MainModel();
                    mainModel.setTitle(childSnapshot.child("headerTitle").getValue(true).toString());
                    GenericTypeIndicator<ArrayList<ChildModel>>genericTypeIndicator = new GenericTypeIndicator<ArrayList<ChildModel>>() {
                        @Override
                        public int hashCode() {
                            return super.hashCode();
                        }
                    };

                    mainModel.setArrayList(childSnapshot.child("listItem").getValue(genericTypeIndicator));
                    MainModelList.add(mainModel);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<MainModel> MainList) {
        MainAdapter adapter = new MainAdapter(this, MainList);
        RVMain.setAdapter(adapter);
    }
    @Override
    public void onFirebaseLoadFailed(String Message){
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }
}