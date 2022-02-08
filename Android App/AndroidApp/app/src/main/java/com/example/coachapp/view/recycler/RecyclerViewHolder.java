package com.example.coachapp.view.recycler;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coachapp.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView exerciseNameTV, muscleNameTV;
    private LinearLayout itemLayout;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        exerciseNameTV = itemView.findViewById(R.id.exerciseNameTV);
        muscleNameTV = itemView.findViewById(R.id.muscleNameTV);
        itemLayout = itemView.findViewById(R.id.itemLayout);
    }
}

