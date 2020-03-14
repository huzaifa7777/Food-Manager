package com.example.health.manager.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StartApp extends ActionBarActivity {

    final static int SELECT_FOOD = 101;

    DBMainAppManager db = new DBMainAppManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startapp_layout);
        startUp();
    }

    @Override
    public void onStart(){
        super.onStart();
        setResults();
    }

    void startUp(){
        ImageButton breakfastImage = (ImageButton) findViewById(R.id.breakfastImgButton);
        ImageButton lunchImage = (ImageButton) findViewById(R.id.lunchImgButton);
        ImageButton snackImage = (ImageButton) findViewById(R.id.snackImgButton);
        ImageButton dinnerImage = (ImageButton) findViewById(R.id.dinnerImgButton);

        breakfastImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBreakfast(v);
            }
        });

        lunchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLunch(v);
            }
        });

        snackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchSnack(v);
            }
        });

        dinnerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDinner(v);
            }
        });
    }

    private void searchBreakfast(View v){
        Intent startIntent = new Intent(StartApp.this, breakfastMainActivity.class);
        startIntent.putExtra("ViewId", v.getId());
        startActivityForResult(startIntent, SELECT_FOOD);
    }

    private void searchLunch(View v){
        Intent startIntent = new Intent(StartApp.this, lunchMainActivity.class);
        startIntent.putExtra("ViewId", v.getId());
        startActivityForResult(startIntent, SELECT_FOOD);
    }

    private void searchSnack(View v){
        Intent startIntent = new Intent(StartApp.this, snackMainActivity.class);
        startIntent.putExtra("ViewId", v.getId());
        startActivityForResult(startIntent, SELECT_FOOD);
    }

    private void searchDinner(View v){
        Intent startIntent = new Intent(StartApp.this, dinnerMainActivity.class);
        startIntent.putExtra("ViewId", v.getId());
        startActivityForResult(startIntent, SELECT_FOOD);
    }

    private int getTotal(String field){
        return db.getTotal(field);
    }

    private void setResults(){
        ((TextView)findViewById(R.id.totalKCal)).setText(String.valueOf(getTotal(DBMainAppContract.mainTable.COLUMN_NAME_COL4)));
        ((TextView)findViewById(R.id.totalCarbohydrates)).setText(String.valueOf(getTotal(DBMainAppContract.mainTable.COLUMN_NAME_COL5)));
        ((TextView)findViewById(R.id.totalFat)).setText(String.valueOf(getTotal(DBMainAppContract.mainTable.COLUMN_NAME_COL6)));
        ((TextView)findViewById(R.id.totalProtein)).setText(String.valueOf(getTotal(DBMainAppContract.mainTable.COLUMN_NAME_COL7)));
    }
}
