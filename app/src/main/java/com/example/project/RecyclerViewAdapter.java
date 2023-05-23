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
    private Context wContext;
    private OnClickListener monClickListener;
    private ArrayList<WildAnimals> wildAnimalsArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name_text, location_text, category_text;
        private ImageView imageView;
        private OnClickListener onClickListener;

        public ViewHolder(View view, OnClickListener onClickListener) {
            super(view);
            name_text = (TextView) view.findViewById(R.id.nameText);
            location_text = (TextView) view.findViewById(R.id.locationText);
            category_text = (TextView) view.findViewById(R.id.categoryText);
            imageView = (ImageView) view.findViewById(R.id.imageview);

            this.onClickListener = onClickListener;
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onClickListener.onClick(wildAnimalsArrayList.get(getAdapterPosition()));
        }
    }

    RecyclerViewAdapter(Context context, ArrayList<WildAnimals> wildAnimalsArrayList, OnClickListener onClickListener) {
        this.wContext = context;
        this.wildAnimalsArrayList = wildAnimalsArrayList;
        this.monClickListener = onClickListener;
    }

    @Override
    @NonNull
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(itemView, monClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String name = wildAnimalsArrayList.get(position).getName();
        holder.name_text.setText(name);
        String location = wildAnimalsArrayList.get(position).getLocation();
        holder.location_text.setText(location);
        String category = wildAnimalsArrayList.get(position).getCategory();
        holder.category_text.setText(category);
        String image = wildAnimalsArrayList.get(position).getAuxdata().getImg();
        Picasso.get().load(image).into(holder.imageView);
        //Picasso.get().load("image_URL").into(imageView);


    }

    @Override
    public int getItemCount() {
        return wildAnimalsArrayList.size();
    }

    public interface OnClickListener {
        void onClick(WildAnimals wildAnimals);
    }
}
