package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WildAnimals> wildAnimalsArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_text, id_text, location_text, category_text;
        public ViewHolder(View view) {
            super(view);
            name_text = (TextView) view.findViewById(R.id.nameText);
            id_text = (TextView) view.findViewById(R.id.idText);
            location_text = (TextView) view.findViewById(R.id.locationText);
            category_text = (TextView) view.findViewById(R.id.categoryText);
        }
    }

    RecyclerViewAdapter(Context context, ArrayList<WildAnimals> wildAnimalsArrayList) {
        this.context = context;
        this.wildAnimalsArrayList = wildAnimalsArrayList;
    }

    @Override
    @NonNull
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String name = wildAnimalsArrayList.get(position).getName();
        holder.name_text.setText(name);
        String id = wildAnimalsArrayList.get(position).getID();
        holder.id_text.setText("ID: " + id);
        String location = wildAnimalsArrayList.get(position).getLocation();
        holder.location_text.setText("Location: " + location);
        String category = wildAnimalsArrayList.get(position).getCategory();
        holder.category_text.setText("Category: " + category);
    }

    @Override
    public int getItemCount() {
        return wildAnimalsArrayList.size();
    }
}
