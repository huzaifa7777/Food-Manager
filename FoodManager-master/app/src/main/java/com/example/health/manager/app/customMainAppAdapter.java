package com.example.health.manager.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class customMainAppAdapter extends BaseAdapter{
    final static int CHANGE_PORTION = 102;

    Model data;

    Toast show;

    DBMainAppManager db;

    Context c;
    int resource;
    int itemName;
    int itemFat;
    int itemCarbohydrates;
    int itemProtein;
    int itemKCal;

    customMainAppAdapter(Context c, int resource, int itemName, int itemFat, int itemCarbohydrates, int itemProtein, int itemKCal, Model data){
        this.c = c;
        this.resource = resource;
        this.itemName = itemName;
        this.itemFat = itemFat;
        this.itemCarbohydrates = itemCarbohydrates;
        this.itemProtein = itemProtein;
        this.itemKCal = itemKCal;
        this.data = data;
        db = new DBMainAppManager(c);
    }

    public void add(MainAppFoodManager object){
        data.add(object);
        notifyDataSetChanged();
    }

    public void del(int pos){
        data.del(pos);
    }

    @Override
    public int getCount(){
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int pos){
        return data.getItem(pos);
    }

    @Override
    public long getItemId(int pos){
        return data.getItemId(pos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = ((Activity) c).getLayoutInflater().inflate(resource, null);
        }
        final MainAppFoodManager item = (MainAppFoodManager) data.getItem(position);

        TextView nameTv = (TextView)convertView.findViewById(itemName);
        TextView fatTv = (TextView)convertView.findViewById(itemFat);
        TextView carbohydratesTv = (TextView)convertView.findViewById(itemCarbohydrates);
        TextView proteinTv = (TextView)convertView.findViewById(itemProtein);
        TextView kCalTv = (TextView)convertView.findViewById(itemKCal);
        nameTv.setText(item.getName());
        fatTv.setText(String.valueOf(item.getFat()));
        carbohydratesTv.setText(String.valueOf(item.getCarbohydrates()));
        proteinTv.setText(String.valueOf(item.getProtein()));
        kCalTv.setText(String.valueOf(item.getKCal()));

        convertView.findViewById(R.id.itemDeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertListen(item);
            }
        });

        convertView.findViewById(R.id.itemEditButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(c, PortionActivity.class);
                startIntent.putExtra("default", item.getPortion());
                startIntent.putExtra("name", item.getName());
                startIntent.putExtra("brand", item.getBrand());
                startIntent.putExtra("meal", item.getMeal());
                ((Activity) c).startActivityForResult(startIntent, CHANGE_PORTION);
            }
        });

        return convertView;
    }

    public void alertListen(final MainAppFoodManager fm){
        AlertDialog.Builder alert = new AlertDialog.Builder(c);
        alert.setTitle("\n" +"Remove");

        alert.setMessage("Are you sure you want to remove this food from the list?");

        alert.setPositiveButton("\n" +"Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(fm);
                if (show != null){
                    show.cancel();
                }
                show = Toast.makeText(c, "\n" +"Removed " + fm.getName() + " from the list", Toast.LENGTH_SHORT);
                show.show();
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