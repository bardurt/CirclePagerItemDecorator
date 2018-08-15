package com.zygne.circlepageindicator.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.zygne.circlepageindicator.R;
import com.zygne.circlepageindicator.adapters.MyModelAdapter;
import com.zygne.circlepageindicator.models.MyModel;
import com.zygne.circlepageindicator.util.CirclePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bardur Thomsen on 8/15/18.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyModelAdapter adapter;
    private List<MyModel> modelList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        modelList = new ArrayList<>();

        for(int i = 1; i < 11; i++){

            modelList.add(new MyModel("" + i));
        }

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MyModelAdapter(this, modelList);
        adapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        CirclePagerIndicator pagerIndicator = new CirclePagerIndicator();
        pagerIndicator.setColors(Color.parseColor("#129ea2"), Color.parseColor("#d8d8d8"));

        recyclerView.addItemDecoration(pagerIndicator);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }
}
