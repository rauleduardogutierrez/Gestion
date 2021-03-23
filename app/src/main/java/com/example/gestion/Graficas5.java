package com.example.gestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.gestion.daos.ApiarioDao;
import com.example.gestion.models.ApiarioViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class Graficas5 extends AppCompatActivity {

    private PieChart mChart;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas5);

        mChart = (PieChart) findViewById(R.id.pieChart);
        mChart.setBackgroundColor(Color.WHITE);

        moveOffScreen();
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setDrawHoleEnabled(true);
        mChart.setMaxAngle(180);
        mChart.setRotationAngle(180);
        mChart.setCenterTextOffset(0,-20);
        setData(4,100);

        mChart.animateY(1000, Easing.EasingOption.EaseInCubic);
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

    }
    String[] countries = new String[]{"india","usa","uk","libia","cuba"};

    private void setData(int count,int range){
        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i=0; i<count;i++){
            float val = (float)((Math.random()*range)+range/5);
            values.add(new PieEntry(val, countries[i]));
        }
        PieDataSet dataSet = new PieDataSet(values, "Partner");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(3f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.invalidate();
    }

    private void moveOffScreen(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;

        int offset = (int)(height*0.5);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mChart.getLayoutParams();
        params.setMargins(0,0,0,-offset);

    }

}