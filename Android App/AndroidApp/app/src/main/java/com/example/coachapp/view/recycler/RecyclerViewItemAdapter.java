package com.example.coachapp.view.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachapp.R;
import com.example.coachapp.model.RecyclerItem;

import java.util.List;


public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<RecyclerItem> itemsList;

    public RecyclerViewItemAdapter(List<RecyclerItem> mItemList) {
        this.itemsList = mItemList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final RecyclerItem item = itemsList.get(position);
        holder.exerciseNameTV.setText(item.getExerciseName());
        holder.muscleNameTV.setText(String.valueOf(item.getMuscleName()));
        holder.itemView.setOnClickListener(view -> {
            // Todo: add detail view
            System.out.println("Position = " + position);
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
