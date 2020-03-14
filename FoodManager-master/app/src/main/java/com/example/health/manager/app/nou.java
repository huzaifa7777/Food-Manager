package com.example.health.manager.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class nou extends ActionBarActivity {

    DBManager db = new DBManager(this);
    FoodManager fm = new FoodManager();
    Toast show;

    TextView foodName;
    TextView foodBrand;
    TextView foodPortion;
    TextView foodKCal;
    TextView foodProtein;
    TextView foodFat;
    TextView foodCarbohydrates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nou);
        startUp();
        check();
        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSomeoneEmpty()){
                    addDB();
                    sendResult();
                }
                else{
                    if (show != null){
                        show.cancel();
                    }
                    show = Toast.makeText(getBaseContext(), "Complete all required fields", Toast.LENGTH_SHORT);
                    show.show();
                }
            }
        });
    }

    boolean isSomeoneEmpty(){
        boolean result = false;
        if (foodName.getText().toString().isEmpty() || foodName.getText().toString().contains(" ") || foodBrand.getText().toString().contains(" ") || foodBrand.getText().toString().isEmpty() || foodPortion.getText().toString().isEmpty() || foodCarbohydrates.getText().toString().isEmpty() || foodKCal.getText().toString().isEmpty() || foodFat.getText().toString().isEmpty() || foodProtein.getText().toString().isEmpty()){
            result = true;
        }

        return result;
    }


    void startUp(){
        foodName = (TextView) findViewById(R.id.foodName);
        foodBrand = (TextView) findViewById(R.id.foodBrand);
        foodPortion = (TextView) findViewById(R.id.foodPortion);
        foodKCal = (TextView) findViewById(R.id.foodKcal);
        foodCarbohydrates = (TextView) findViewById(R.id.foodCarbo);
        foodFat = (TextView) findViewById(R.id.foodFat);
        foodProtein = (TextView) findViewById(R.id.foodProtein);
    }

    void check(){

        foodBrand.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodBrand.getText().toString().matches("")) {
                        foodBrand.setError("You must enter a brand");
                        return true;
                    }
                    else if (foodBrand.getText().toString().contains(" ")){
                        foodBrand.setError("Do not enter whitespace");
                        return true;
                    }
                }
                return false;
            }
        });

        foodBrand.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodBrand.getText().toString().matches("")) {
                        foodBrand.setError("\n" + "You must enter a brand");
                        return true;
                    }
                    else if (foodBrand.getText().toString().contains(" ")) {
                        foodBrand.setError("Do not enter whitespace");
                        return true;
                    }
                }
                return false;
            }
        });

        foodName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodName.getText().toString().matches("")) {
                        foodName.setError("You must enter a name");
                        return true;
                    }
                    else if (foodName.getText().toString().contains(" ")) {
                        foodName.setError("Do not enter whitespace");
                        return true;
                    }
                }
                return false;
            }
        });

        foodName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodName.getText().toString().matches("")) {
                        foodName.setError("You must enter a name");
                        return true;
                    }
                    else if (foodName.getText().toString().contains(" ")) {
                        foodName.setError("\n" + "Do not enter whitespace");
                        return true;
                    }
                }
                return false;
            }
        });

        foodPortion.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodPortion.getText().toString().matches("")) {
                        foodPortion.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodPortion.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodPortion.getText().toString().matches("")) {
                        foodPortion.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodKCal.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodKCal.getText().toString().matches("")) {
                        foodKCal.setError("You must enter a number ");
                        return true;
                    }
                }
                return false;
            }
        });

        foodKCal.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodKCal.getText().toString().matches("")) {
                        foodKCal.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodCarbohydrates.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodCarbohydrates.getText().toString().matches("")) {
                        foodCarbohydrates.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodCarbohydrates.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodCarbohydrates.getText().toString().matches("")) {
                        foodCarbohydrates.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodProtein.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodProtein.getText().toString().matches("")) {
                        foodProtein.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodProtein.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodProtein.getText().toString().matches("")) {
                        foodProtein.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodFat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (foodFat.getText().toString().matches("")) {
                        foodFat.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });

        foodFat.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (foodFat.getText().toString().matches("")) {
                        foodFat.setError("You must enter a number");
                        return true;
                    }
                }
                return false;
            }
        });
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

    protected void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    void addDB(){
        fm.setName(((EditText) findViewById(R.id.foodName)).getText().toString());
        fm.setBrand(((EditText) findViewById(R.id.foodBrand)).getText().toString());
        fm.setPortion(Integer.parseInt(((EditText) findViewById(R.id.foodPortion)).getText().toString()));
        fm.setKCal(Integer.parseInt(((EditText) findViewById(R.id.foodKcal)).getText().toString()));
        fm.setCarbohydrates(Integer.parseInt(((EditText) findViewById(R.id.foodCarbo)).getText().toString()));
        fm.setFat(Integer.parseInt(((EditText) findViewById(R.id.foodFat)).getText().toString()));
        fm.setProtein(Integer.parseInt(((EditText) findViewById(R.id.foodProtein)).getText().toString()));

        db.insert(fm);
    }
}
