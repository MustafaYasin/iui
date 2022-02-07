package com.example.coachapp.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachapp.R;
import com.example.coachapp.model.RecyclerItem;
import com.example.coachapp.view.recycler.RecyclerViewItemAdapter;

import java.util.LinkedList;
import java.util.List;

public class TrainingsplanView extends Fragment implements View.OnClickListener {

    private List<RecyclerItem> itemsList = new LinkedList<>();
    private RecyclerViewItemAdapter recyclerViewItemAdapter1;
    private RecyclerView recyclerView1;
    private Button lastClickedButton;

    private TextView setCountTV;
    private TextView repCountTV;
    private TextView levelTV;

    private Button day1;
    private Button day2;
    private Button day3;
    private Button day4;
    private Button day5;
    private Button day6;
    private Button day7;
    private int darkPurple = Color.parseColor("#FF6604F1");
    private int lightPurple = Color.parseColor("#FF714AB6");

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
        day1 = view.findViewById(R.id.day1Button);
        day2 = view.findViewById(R.id.day2Button);
        day3 = view.findViewById(R.id.day3Button);
        day4 = view.findViewById(R.id.day4Button);
        day5 = view.findViewById(R.id.day5Button);
        day6 = view.findViewById(R.id.day6Button);
        day7 = view.findViewById(R.id.day7Button);

        day1.setOnClickListener(this);
        day2.setOnClickListener(this);
        day3.setOnClickListener(this);
        day4.setOnClickListener(this);
        day5.setOnClickListener(this);
        day6.setOnClickListener(this);
        day7.setOnClickListener(this);

        lastClickedButton = day1;
        day1.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.day1Button:
                System.out.println("Button 1");
                lastClickedButton = day1;
                day1.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.day2Button:
                System.out.println("Button 2");
                lastClickedButton = day2;
                day2.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.day3Button:
                System.out.println("Button 3");
                lastClickedButton = day3;
                day3.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.day4Button:
                System.out.println("Button 4");
                lastClickedButton = day4;
                day4.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.day5Button:
                System.out.println("Button 5");
                lastClickedButton = day5;
                day5.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.day6Button:
                System.out.println("Button 6");
                lastClickedButton = day6;
                day6.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
            case R.id.day7Button:
                System.out.println("Button 7");
                lastClickedButton = day7;
                day7.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                break;
        }
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