package com.example.health.manager.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class breakfastMainActivity extends ActionBarActivity {

    final static int SELECT_FOOD = 101;
    DBMainAppManager db = new DBMainAppManager(this);
    Model breakfastDM;
    customMainAppAdapter adapterBreakfast;
    ListView lvBreakfast;
    Toast show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_mainactivity);
        setUp();
        navigate();
    }

    @Override
    protected void onStart(){
        super.onStart();
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newProduct:
                searchProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setUp(){
        breakfastDM = new Model();
        lvBreakfast = (ListView)findViewById(R.id.conjuntbreakfast);
        lvBreakfast.setItemsCanFocus(true);
        adapterBreakfast = new customMainAppAdapter(this, R.layout.custom_listview, R.id.itemName, R.id.itemFat, R.id.itemCarbohydrates, R.id.itemProtein, R.id.itemKCal, breakfastDM);
        lvBreakfast.setAdapter(adapterBreakfast);
        lvBreakfast.setFocusableInTouchMode(true);
        lvBreakfast.setFocusable(true);

        lvBreakfast.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startIntent = new Intent(breakfastMainActivity.this, PortionActivity.class);
                MainAppFoodManager item = (MainAppFoodManager) adapterBreakfast.getItem(position);
                startIntent.putExtra("default", item.getPortion());
                startIntent.putExtra("name", item.getName());
                startIntent.putExtra("brand", item.getBrand());
                startIntent.putExtra("meal", item.getMeal());
                startActivityForResult(startIntent, customMainAppAdapter.CHANGE_PORTION);
                return true;
            }
        });

        refresh();
    }

    public void navigate() {
        findViewById(R.id.Breakfastfab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProduct();
            }
        });
    }

    private void searchProduct(){
        Intent startIntent = new Intent(breakfastMainActivity.this, llistaproductes.class);
        startActivityForResult(startIntent, SELECT_FOOD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FOOD) {
                Bundle result = data.getExtras();
                if (result != null) {
                    MainAppFoodManager fm;
                    fm = new MainAppFoodManager(result.getString("foodName"), result.getString("foodBrand"), result.getInt("foodPortion"), result.getInt("foodKCal"), result.getInt("foodCarbohydrates"), result.getInt("foodFat"), result.getInt("foodProtein"), "breakfast");
                    long add = db.insert(fm);
                    if (add != -1){
                        if (show != null){
                            show.cancel();
                        }
                        CharSequence text = "Added " + fm.getName() + " in the product list";
                        show = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        show.show();

                        refresh();
                    }
                    else{
                        if (show != null){
                            show.cancel();
                        }
                        CharSequence text = "The product " + fm.getName() + " is on the list";
                        show = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        show.show();
                    }
                }
            }
            else if(requestCode == customMainAppAdapter.CHANGE_PORTION){
                Bundle result = data.getExtras();
                if(result != null){
                    updateItem(result.getString("name"), result.getString("brand"), result.getString("meal"), result.getInt("Portion"));
                }
            }
        }
    }

    public void refresh(){
        ArrayList<MainAppFoodManager> ar = db.query("breakfast", DBMainAppContract.mainTable.COLUMN_NAME_COL8);
        breakfastDM = new Model();
        adapterBreakfast = new customMainAppAdapter(this, R.layout.custom_listview, R.id.itemName, R.id.itemFat, R.id.itemCarbohydrates, R.id.itemProtein, R.id.itemKCal, breakfastDM);
        if(ar != null) {
            for (int i = 0; i < ar.size(); i++) {
                adapterBreakfast.add(ar.get(i));
            }
            lvBreakfast.setAdapter(adapterBreakfast);
        }
    }

    private void updateItem(String name, String brand, String meal, int newPortion){
        MainAppFoodManager fm = db.query(name, brand, meal);

        DBManager dbm = new DBManager(this);
        FoodManager fmAux = dbm.query(fm.getName(), fm.getBrand());

        float factor;
        if(newPortion != 0 && fm.getPortion() != 0) {
            factor = (float)newPortion / (float)fm.getPortion();
            fm.setFat(Math.round(fmAux.getFat() * factor));
            fm.setKCal(Math.round(fmAux.getKCal() * factor));
            fm.setProtein(Math.round(fmAux.getProtein() * factor));
            fm.setCarbohydrates(Math.round(fmAux.getCarbohydrates() * factor));

            db.update(fm);
        }
    }
}
