package com.example.expensemanager.views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.expensemanager.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    Float expense,total,income;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        expense = Float.parseFloat(getIntent().getStringExtra("expense"));
        total = Float.parseFloat(getIntent().getStringExtra("total"));
        income = Float.parseFloat(getIntent().getStringExtra("income"));

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(expense,2016));
        data.add(new PieEntry(total,2016));
        data.add(new PieEntry(income,2016));

        PieDataSet pieDataSet = new PieDataSet(data,"data");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Transactions");
        pieChart.animate();
    }
}