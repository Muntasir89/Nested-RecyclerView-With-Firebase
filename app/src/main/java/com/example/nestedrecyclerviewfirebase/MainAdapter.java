package com.example.nestedrecyclerviewfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    Context context;
    List<MainModel> ChildList;

    public MainAdapter(Context context, List<MainModel> childRVList) {
        this.context = context;
        ChildList = childRVList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(ChildList.get(position).getTitle());
        List<ChildModel>ChildData = ChildList.get(position).getArrayList();
        ChildAdapter childAdapter = new ChildAdapter(context, ChildData);
        holder.RVChild.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.RVChild.setAdapter(childAdapter);
        holder.RVChild.setNestedScrollingEnabled(false);
        //Button More
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button More : "+holder.txtTitle.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return ChildList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        RecyclerView RVChild;
        Button btn_more;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            RVChild = itemView.findViewById(R.id.RVChild);
            btn_more = itemView.findViewById(R.id.btnMore);
        }
    }
}