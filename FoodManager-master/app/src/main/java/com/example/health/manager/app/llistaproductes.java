package com.example.health.manager.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class llistaproductes extends ActionBarActivity {

    final static int NEW_FOOD = 201;
    final static int INFO_FOOD = 202;
    DBManager db = new DBManager(this);
    ArrayAdapter<String> adapter;
    String querySave = null;
    Toast show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistaproductes);

        search_view();

        add_button();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == NEW_FOOD || requestCode == INFO_FOOD){
                Bundle result = data.getExtras();
                if(result != null){
                    sendResult(result);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_llista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newUserProduct:
                newFood();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void search_view(){
        SearchView search = (SearchView) findViewById(R.id.searchView);
        search.setQueryHint("Search View");
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (show != null) {
                    show.cancel();
                }
                CharSequence text = "\n" + "Searching for: " + query;
                show = Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT);
                show.show();
                querySave = query;
                query(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("savedQuery", querySave);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.size() != 0) {
            super.onRestoreInstanceState(savedInstanceState);
            querySave = savedInstanceState.getString("savedQuery");
            if(querySave != null) {
                query(querySave);
            }
        }
    }

    public void query(String q){
        ArrayList<FoodManager> ar = db.query(q);
        createAdapter(ar);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void sendResult(Bundle food){
        Intent result = new Intent();
        result.putExtra("foodName", food.getString("foodName"));
        result.putExtra("foodBrand", food.getString("foodBrand"));
        result.putExtra("foodPortion", food.getInt("foodPortion"));
        result.putExtra("foodKCal", food.getInt("foodKCal"));
        result.putExtra("foodCarbohydrates", food.getInt("foodCarbohydrates"));
        result.putExtra("foodFat", food.getInt("foodFat"));
        result.putExtra("foodProtein", food.getInt("foodProtein"));

        setResult(Activity.RESULT_OK, result);
        finish();
    }

    void createAdapter(ArrayList<FoodManager> ar){
        if(!ar.isEmpty()) {
            ArrayList<String> dades = new ArrayList<>();
            for(int i = 0; i < ar.size(); i++){
                dades.add((ar.get(i)).getName() + " " + (ar.get(i)).getBrand());
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dades);
            ListView lv = (ListView) findViewById(R.id.listView);
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String[] patch = ((String) parent.getItemAtPosition(position)).split(" ");
                    infoFood(patch[0], patch[1]);
                }
            });
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    String[] patch = ((String) parent.getItemAtPosition(position)).split(" ");
                    alertListen(patch[0], patch[1]);
                    return true;
                }
            });
        }
        else{
            //ficarem toooooast toaaast...
        }
    }

    void add_button(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFood();
            }
        });
    }

    private void newFood(){
        Intent startIntent = new Intent(llistaproductes.this, nou.class);
        startActivityForResult(startIntent, NEW_FOOD);
    }

    private void infoFood(String nom, String brand){
        Intent startIntent = new Intent(llistaproductes.this, FoodInfoActivity.class);
        startIntent.putExtra("NomId", nom);
        startIntent.putExtra("BrandId", brand);
        startActivityForResult(startIntent, INFO_FOOD);
    }

    public void alertListen(final String name, final String brand){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove");
        alert.setMessage("\n" + "Are you sure you want to remove this food from the list?");
        alert.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(name, brand);
                if (show != null) {
                    show.cancel();
                }
                show = Toast.makeText(getBaseContext(), "Removed " + name + " of the record", Toast.LENGTH_SHORT);
                show.show();
                query(querySave);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }
}
