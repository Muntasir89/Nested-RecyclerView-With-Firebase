package com.example.nestedrecyclerviewfirebase;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    Context context;
    List<ChildModel>ChildDataList;
    public ChildAdapter(){

    }
    public ChildAdapter(Context context, List<ChildModel> childDataList) {
        this.context = context;
        ChildDataList = childDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.txt_item_title.setText(ChildDataList.get(position).getName());
        Glide.with(context).load(ChildDataList.get(position).getImgUrl()).into(holder.img);
        //Item Click Listener
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(context, ""+ChildDataList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ChildDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView txt_item_title;

        ItemClickListener itemClickListener;
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title = itemView.findViewById(R.id.itemTitle);
            img = itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }
}

