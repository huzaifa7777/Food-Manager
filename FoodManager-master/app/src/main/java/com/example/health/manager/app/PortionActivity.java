package com.example.health.manager.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.NumberPicker;

public class PortionActivity extends ActionBarActivity {
    String localName, localBrand, localMeal = null;

    //  onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portion_layout);

        final NumberPicker np = (NumberPicker) findViewById(R.id.portionPicker);

        setListeners(np);

        setNumberPicker(np);
    }

    //Save and restore instances
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("savedPortion", ((NumberPicker) findViewById(R.id.portionPicker)).getValue());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.size() != 0) {
            super.onRestoreInstanceState(savedInstanceState);
            ((NumberPicker) findViewById(R.id.portionPicker)).setValue(savedInstanceState.getInt("savedPortion"));
        }
    }

    // Function to manage the numberPicker in the activity
    void setNumberPicker(NumberPicker np) {
        np.setMaxValue(999);
        np.setMinValue(1);
        np.setFocusable(true);
        np.setFocusableInTouchMode(true);
        setPicker(np);
    }


    // Search with in the intent that created this activity the default value for our numberPicker or sets it to 0
    private void setPicker(NumberPicker np) {
        Intent asked = getIntent();
        Bundle data = asked.getExtras();
        if (data != null){
            np.setValue(data.getInt("default"));
            localName = data.getString("name");
            localBrand = data.getString("brand");
            localMeal = data.getString("meal");
        }
        else np.setValue(0);
    }

    // Sends the selected value to the activity that called this class
    private void sendResult(int q) {
        Intent result = new Intent();
        result.putExtra("Portion", q);
        if(localName != null && localMeal != null && localBrand != null){
            result.putExtra("name", localName);
            result.putExtra("brand", localBrand);
            result.putExtra("meal", localMeal);
        }
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    // Sets the function for the button listeners
    private void setListeners(final NumberPicker np){
        findViewById(R.id.portionCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        findViewById(R.id.portionSaveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(np.getValue());
            }
        });
    }
}
