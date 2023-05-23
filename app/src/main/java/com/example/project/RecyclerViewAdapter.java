package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnClickListener monClickListener;
    private ArrayList<WildAnimals> wildAnimalsArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name_text;
        private OnClickListener onClickListener;

        public ViewHolder(View view, OnClickListener onClickListener) {
            super(view);
            name_text = (TextView) view.findViewById(R.id.nameText);
            this.onClickListener = onClickListener;
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onClickListener.onClick(wildAnimalsArrayList.get(getAdapterPosition()));
        }
    }

    RecyclerViewAdapter(ArrayList<WildAnimals> wildAnimalsArrayList, OnClickListener onClickListener) {
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

    }

    @Override
    public int getItemCount() {
        return wildAnimalsArrayList.size();
    }

    public interface OnClickListener {
        void onClick(WildAnimals wildAnimals);
    }
}
