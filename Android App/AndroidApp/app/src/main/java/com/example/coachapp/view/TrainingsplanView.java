package com.example.coachapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachapp.R;
import com.example.coachapp.model.RecyclerItem;
import com.example.coachapp.view.recycler.ClickListener;
import com.example.coachapp.view.recycler.RecyclerViewItemAdapter;

import java.util.LinkedList;
import java.util.List;

public class TrainingsplanView extends Fragment {

    private List<RecyclerItem> itemsList = new LinkedList<>();
    RecyclerViewItemAdapter recyclerViewItemAdapter1;
    RecyclerView recyclerView1;

    private TextView setCountTV;
    private TextView repCountTV;
    private TextView levelTV;

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
        setCountTV = view.findViewById(R.id.setCountTV);
        repCountTV = view.findViewById(R.id.repCountTV);
        levelTV = view.findViewById(R.id.levelTV);
        setUpRecyclerViews(view, R.id.recyclerView1);
//        setUpRecyclerViews(view, R.id.recyclerView2);
//        setUpRecyclerViews(view, R.id.recyclerView3);
//        setUpRecyclerViews(view, R.id.recyclerView4);
//        setUpRecyclerViews(view, R.id.recyclerView5);
//        setUpRecyclerViews(view, R.id.recyclerView6);
//        setUpRecyclerViews(view, R.id.recyclerView7);

//        recyclerViewItemAdapter1.setOnItemClickListener((view1, data, position) -> {
//            System.out.println("hi");
//            Toast.makeText(getActivity(), "Position = "+ position, Toast.LENGTH_SHORT).show();
//        });

    }

    private void addItemsToRecyclerView() {
        for (int i = 0; i < 10; i++) {
            RecyclerItem items = new RecyclerItem("Exercise" + i, "muscle" + i);
            itemsList.add(items);
        }
    }

    private void setUpRecyclerViews(View view, int recyclerViewId) {
        recyclerView1 = view.findViewById(recyclerViewId);
        recyclerViewItemAdapter1 = new RecyclerViewItemAdapter(itemsList);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView1.setAdapter(recyclerViewItemAdapter1);
    }
}