package com.example.health.manager.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FoodInfoActivity extends ActionBarActivity {

    final static int SET_QUANTITY = 301;

    int portion = 0;

    FoodManager fm = new FoodManager();
    DBManager dbm = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacio_aliment);
        getFoodInfo();
        findViewById(R.id.infoSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult();
            }
        });
        findViewById(R.id.infoCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        findViewById(R.id.infoPortion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuantity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == SET_QUANTITY){
                Bundle result = data.getExtras();
                if(result != null){
                    updateItem(result.getInt("Portion"));
                }
            }
        }
        else{
            ((TextView)findViewById(R.id.infoPortion)).setText(String.valueOf(portion));
        }
    }

    private void updateItem(int newPortion){
        float factor;
        if(newPortion != 0 && fm.getPortion() != 0) {

            FoodManager fmAux = dbm.query(fm.getName(), fm.getBrand());

            factor = (float)newPortion / (float)fm.getPortion();
            fm.setFat(Math.round(fmAux.getFat() * factor));
            fm.setKCal(Math.round(fmAux.getKCal() * factor));
            fm.setProtein(Math.round(fmAux.getProtein() * factor));
            fm.setCarbohydrates(Math.round(fmAux.getCarbohydrates() * factor));

            ((TextView)findViewById(R.id.infoPortion)).setText(String.valueOf(newPortion));
            ((TextView)findViewById(R.id.infoCarbo)).setText(String.valueOf(fm.getCarbohydrates()));
            ((TextView)findViewById(R.id.infoFat)).setText(String.valueOf(fm.getFat()));
            ((TextView)findViewById(R.id.infoKcal)).setText(String.valueOf(fm.getKCal()));
            ((TextView)findViewById(R.id.infoProtein)).setText(String.valueOf(fm.getProtein()));
        }
    }

    //Save and restore instances
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("savedPortion", portion);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.size() != 0) {
            super.onRestoreInstanceState(savedInstanceState);
            ((TextView)findViewById(R.id.infoPortion)).setText(String.valueOf(savedInstanceState.getInt("savedPortion")));
        }
    }

    private void setQuantity(){
        Intent startIntent = new Intent(FoodInfoActivity.this, PortionActivity.class);
        startIntent.putExtra("default", portion);
        startActivityForResult(startIntent, SET_QUANTITY);
    }

    private void sendResult(){
        Intent result = new Intent();
        result.putExtra("foodName", fm.getName());
        result.putExtra("foodBrand", fm.getBrand());
        result.putExtra("foodPortion", fm.getPortion());
        result.putExtra("foodKCal", fm.getKCal());
        result.putExtra("foodCarbohydrates", fm.getCarbohydrates());
        result.putExtra("foodFat", fm.getFat());
        result.putExtra("foodProtein", fm.getProtein());
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private void getFoodInfo(){
        Intent asked = getIntent();
        Bundle data = asked.getExtras();
        if(data != null){
            String name = data.getString("NomId");
            String brand = data.getString("BrandId");

            fm = dbm.query(name, brand);

            ((TextView)findViewById(R.id.infoName)).setText(fm.getName());
            ((TextView)findViewById(R.id.infoBrand)).setText(fm.getBrand());
            ((TextView)findViewById(R.id.infoPortion)).setText(String.valueOf(fm.getPortion()));
            ((TextView)findViewById(R.id.infoKcal)).setText(String.valueOf(fm.getKCal()));
            ((TextView)findViewById(R.id.infoCarbo)).setText(String.valueOf(fm.getCarbohydrates()));
            ((TextView)findViewById(R.id.infoFat)).setText(String.valueOf(fm.getFat()));
            ((TextView)findViewById(R.id.infoProtein)).setText(String.valueOf(fm.getProtein()));
            portion = fm.getPortion();
        }
    }
}
