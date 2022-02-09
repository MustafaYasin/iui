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
import com.example.coachapp.connection.Routes;
import com.example.coachapp.model.Exercise;
import com.example.coachapp.model.RecyclerItem;
import com.example.coachapp.model.TrainingsDay;
import com.example.coachapp.view.recycler.RecyclerViewItemAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainingsplanView extends Fragment implements View.OnClickListener {

    private List<RecyclerItem> itemsList = new LinkedList<>();
    private List<RecyclerItem> itemsList2 = new LinkedList<>();
    private List<RecyclerItem> itemsList3 = new LinkedList<>();
    private List<RecyclerItem> itemsList4 = new LinkedList<>();
    private List<RecyclerItem> itemsList5 = new LinkedList<>();
    private List<RecyclerItem> itemsList6 = new LinkedList<>();
    private List<RecyclerItem> itemsList7 = new LinkedList<>();
    private RecyclerViewItemAdapter recyclerViewItemAdapter1;
    private RecyclerViewItemAdapter recyclerViewItemAdapter2;
    private RecyclerViewItemAdapter recyclerViewItemAdapter3;
    private RecyclerViewItemAdapter recyclerViewItemAdapter4;
    private RecyclerViewItemAdapter recyclerViewItemAdapter5;
    private RecyclerViewItemAdapter recyclerViewItemAdapter6;
    private RecyclerViewItemAdapter recyclerViewItemAdapter7;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private RecyclerView recyclerView5;
    private RecyclerView recyclerView6;
    private RecyclerView recyclerView7;

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

    private int trainingsdayClicked = 1;
    private ArrayList<TrainingsDay> trainingsdays;
    private RecyclerView lastRecyclerView;

    public TrainingsplanView() {
    }

    String test = "test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainingsdays = Routes.getMyTrainingsPlan().getTrainingsDays();
        addItemsToRecyclerView(1);
        addItemsToRecyclerView(2);
        addItemsToRecyclerView(3);
        addItemsToRecyclerView(4);
        addItemsToRecyclerView(5);
        addItemsToRecyclerView(6);
        addItemsToRecyclerView(7);
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
        trainingsdayClicked = 1;

        day1.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
        setUpRecyclerViews(view);
        lastRecyclerView = recyclerView1;
       /* setUpRecyclerViews(view, R.id.recyclerView2);
        setUpRecyclerViews(view, R.id.recyclerView3);
        setUpRecyclerViews(view, R.id.recyclerView4);
        setUpRecyclerViews(view, R.id.recyclerView5);
        setUpRecyclerViews(view, R.id.recyclerView6);
        setUpRecyclerViews(view, R.id.recyclerView7);*/


        //fill trainingsplan
        setCountTV.setText(String.valueOf(Routes.getMyTrainingsPlan().getSets()));
        repCountTV.setText("8-12");
        levelTV.setText(Routes.getMyTrainingsPlan().getLevel());


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
                trainingsdayClicked = 1;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView1.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView1;
                break;
            case R.id.day2Button:
                System.out.println("Button 2");
                lastClickedButton = day2;
                day2.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                //addItemsToRecyclerView(2);
                trainingsdayClicked = 2;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView2.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView2;
                break;
            case R.id.day3Button:
                System.out.println("Button 3");
                lastClickedButton = day3;
                day3.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                trainingsdayClicked = 3;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView3.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView3;
                break;
            case R.id.day4Button:
                System.out.println("Button 4");
                lastClickedButton = day4;
                day4.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                trainingsdayClicked = 4;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView4.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView4;
                break;
            case R.id.day5Button:
                System.out.println("Button 5");
                lastClickedButton = day5;
                day5.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                trainingsdayClicked = 5;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView5.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView5;
                break;
            case R.id.day6Button:
                System.out.println("Button 6");
                lastClickedButton = day6;
                day6.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                trainingsdayClicked = 6;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView6.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView6;
                break;
            case R.id.day7Button:
                System.out.println("Button 7");
                lastClickedButton = day7;
                day7.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
                lastClickedButton.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
                trainingsdayClicked = 7;
                lastRecyclerView.setVisibility(View.INVISIBLE);
                recyclerView7.setVisibility(View.VISIBLE);
                lastRecyclerView = recyclerView7;
                break;
        }
    }

    private void addItemsToRecyclerView(int trainingsdayClicked) {
        switch (trainingsdayClicked) {
            case 1:
                ArrayList<Exercise> exerciseList = trainingsdays.get(0).getExercises();
                for (int i = 0; i < exerciseList.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList.get(i).getTitle(), "muscle" + i);
                    itemsList.add(items);
                }
                break;
            case 2:
                ArrayList<Exercise> exerciseList2 = trainingsdays.get(1).getExercises();
                for (int i = 0; i < exerciseList2.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList2.get(i).getTitle(), "muscle" + i);
                    itemsList2.add(items);
                }


                break;
            case 3:
                ArrayList<Exercise> exerciseList3 = trainingsdays.get(2).getExercises();
                for (int i = 0; i < exerciseList3.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList3.get(i).getTitle(), "muscle" + i);
                    itemsList3.add(items);
                }

                break;
            case 4:
                ArrayList<Exercise> exerciseList4 = trainingsdays.get(3).getExercises();
                for (int i = 0; i < exerciseList4.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList4.get(i).getTitle(), "muscle" + i);
                    itemsList4.add(items);
                }
                break;
            case 5:
                ArrayList<Exercise> exerciseList5 = trainingsdays.get(4).getExercises();
                for (int i = 0; i < exerciseList5.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList5.get(i).getTitle(), "muscle" + i);
                    itemsList5.add(items);
                }
                break;
            case 6:
                ArrayList<Exercise> exerciseList6 = trainingsdays.get(5).getExercises();
                for (int i = 0; i < exerciseList6.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList6.get(i).getTitle(), "muscle" + i);
                    itemsList6.add(items);
                }
                break;
            case 7:
                ArrayList<Exercise> exerciseList7 = trainingsdays.get(6).getExercises();
                for (int i = 0; i < exerciseList7.size(); i++) {
                    RecyclerItem items = new RecyclerItem(exerciseList7.get(i).getTitle(), "muscle" + i);
                    itemsList7.add(items);
                }
                break;

        }

    }

    private void setUpRecyclerViews(View view) {
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerViewItemAdapter1 = new RecyclerViewItemAdapter(itemsList);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView1.setAdapter(recyclerViewItemAdapter1);

        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerViewItemAdapter2 = new RecyclerViewItemAdapter(itemsList2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView2.setAdapter(recyclerViewItemAdapter2);
        recyclerView2.setVisibility(View.VISIBLE);

        recyclerView3 = view.findViewById(R.id.recyclerView3);
        recyclerViewItemAdapter3 = new RecyclerViewItemAdapter(itemsList3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView3.setAdapter(recyclerViewItemAdapter3);

        recyclerView4 = view.findViewById(R.id.recyclerView4);
        recyclerViewItemAdapter4 = new RecyclerViewItemAdapter(itemsList4);
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView4.setAdapter(recyclerViewItemAdapter4);

        recyclerView5 = view.findViewById(R.id.recyclerView5);
        recyclerViewItemAdapter5 = new RecyclerViewItemAdapter(itemsList5);
        recyclerView5.setHasFixedSize(true);
        recyclerView5.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView5.setAdapter(recyclerViewItemAdapter5);

        recyclerView6 = view.findViewById(R.id.recyclerView6);
        recyclerViewItemAdapter6 = new RecyclerViewItemAdapter(itemsList6);
        recyclerView6.setHasFixedSize(true);
        recyclerView6.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView6.setAdapter(recyclerViewItemAdapter6);

        recyclerView7 = view.findViewById(R.id.recyclerView7);
        recyclerViewItemAdapter7 = new RecyclerViewItemAdapter(itemsList7);
        recyclerView7.setHasFixedSize(true);
        recyclerView7.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView7.setAdapter(recyclerViewItemAdapter7);
    }
}