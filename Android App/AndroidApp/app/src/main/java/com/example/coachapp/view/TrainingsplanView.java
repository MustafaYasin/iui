package com.example.coachapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachapp.R;
import com.example.coachapp.model.RecyclerItem;

import java.util.LinkedList;
import java.util.List;

public class TrainingsplanView extends Fragment {

    private List<RecyclerItem> itemsList = new LinkedList<>();

    public TrainingsplanView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addItemsToRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trainingsplan, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpRecyclerViews(view, R.id.recyclerView1);
        setUpRecyclerViews(view, R.id.recyclerView2);
        setUpRecyclerViews(view, R.id.recyclerView3);
        setUpRecyclerViews(view, R.id.recyclerView4);
        setUpRecyclerViews(view, R.id.recyclerView5);
        setUpRecyclerViews(view, R.id.recyclerView6);
        setUpRecyclerViews(view, R.id.recyclerView7);
    }

    private void addItemsToRecyclerView() {
        for (int i = 0; i < 10; i++) {
            RecyclerItem items = new RecyclerItem("Exercise" + i, "muscle" + i);
            itemsList.add(items);
        }
    }

    private void setUpRecyclerViews(View view, int recyclerViewId) {
        RecyclerView recyclerView = view.findViewById(recyclerViewId);
        RecyclerViewItemAdapter recyclerViewItemAdapter = new RecyclerViewItemAdapter(itemsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerViewItemAdapter);
    }
}