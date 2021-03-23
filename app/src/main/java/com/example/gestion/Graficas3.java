package com.example.gestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.gestion.daos.ApiarioDao;
import com.example.gestion.database.AppDatabaseApi;
import com.example.gestion.entities.Apiario;
import com.example.gestion.models.ApiarioViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public  class Graficas3 extends AppCompatActivity{

    private BarChart barChart;
    private PieChart pieChart;
    private ApiarioViewModel apiarioViewModel;

    Button btngraficas;
    List lista;
    View view;

    private String[]establecimientos= new String[]{"ENERO","FEBRERO","MARZO","ABRIL","MAYO","tt","uu"};
    private int[]sale= new int[]{25,20,38,10,15,12,11};
    private int[]colors= new int[]{Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.LTGRAY,Color.MAGENTA,Color.DKGRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas3);

        barChart = (BarChart) findViewById(R.id.barChart);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        //BOTON
        /*
        btngraficas = (Button) findViewById(R.id.btngraficas);
        btngraficas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


            }
        });
        */
        //BOTON
        
        createCharts();
    }
    //ApiarioViewModel model = new ViewModelProvider(Graficas3.this).get(ApiarioViewModel.class);

    //public void onChanged(String establecimientos) {
        //List<Apiario> getEstablecimientos;
    //}



    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextColor(textColor);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }

    private void legend(Chart chart){
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i=0; i<establecimientos.length;i++){
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors[i];
            entry.label=establecimientos[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry>entries = new ArrayList<>();
        for (int i=0; i<sale.length;i++)
            entries.add(new BarEntry(i,sale[i]));
        return entries;
    };

    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry>entries = new ArrayList<>();
        for (int i=0; i<sale.length;i++)
            entries.add(new PieEntry(sale[i]));
        return entries;
    };
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(establecimientos));
        axis.setEnabled(false);
    }
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);
        axis.setGranularity(20);
    }

    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }

    public void createCharts(){
        barChart = (BarChart)getSameChart(barChart,"series",Color.RED,Color.CYAN,3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());
        //barChart.getLegend().setEnabled(false);

        pieChart = (PieChart)getSameChart(pieChart,"ventas",Color.GRAY,Color.MAGENTA,3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(false);
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.YELLOW);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet = (BarDataSet)getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet = (PieDataSet)getData(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }


}