package com.example.health.manager.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBMainAppManager {
    private DBMainAppHelper dbHelper = null;

    public DBMainAppManager(Context context) {
        dbHelper = DBMainAppHelper.getInstance(context);
    }

    // Insert method
    public long insert(MainAppFoodManager fm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL1, fm.getName());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL2, fm.getBrand());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL3, fm.getPortion());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL4, fm.getKCal());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL5, fm.getCarbohydrates());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL6, fm.getFat());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL7, fm.getProtein());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL8, fm.getMeal());

        return db.insert(DBMainAppContract.mainTable.TABLE_NAME, null, values);
    }

    public void update(MainAppFoodManager fm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL1, fm.getName());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL2, fm.getBrand());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL3, fm.getPortion());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL4, fm.getKCal());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL5, fm.getCarbohydrates());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL6, fm.getFat());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL7, fm.getProtein());
        values.put(DBMainAppContract.mainTable.COLUMN_NAME_COL8, fm.getMeal());

        db.update(DBMainAppContract.mainTable.TABLE_NAME,values, DBMainAppContract.mainTable.COLUMN_NAME_COL1 + "='" + fm.getName() + "' and " + DBMainAppContract.mainTable.COLUMN_NAME_COL2 + "='" + fm.getBrand() + "' and " + DBMainAppContract.mainTable.COLUMN_NAME_COL8 + "='" + fm.getMeal() + "'", null);
    }

    //delete method
    public void delete(MainAppFoodManager fm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(DBMainAppContract.mainTable.TABLE_NAME, DBMainAppContract.mainTable.COLUMN_NAME_COL1 + "='" + fm.getName() + "' and " + DBMainAppContract.mainTable.COLUMN_NAME_COL2 + "='" + fm.getBrand() + "' and " + DBMainAppContract.mainTable.COLUMN_NAME_COL8 + "='" + fm.getMeal() + "'", null);
        db.close();
    }

    // select methods
    public ArrayList<MainAppFoodManager> query(String searchName){
        ArrayList<MainAppFoodManager> ar = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBContract.TaulaAliments.TABLE_NAME + " WHERE " + DBContract.TaulaAliments.COLUMN_NAME_COL1 + "='" + searchName + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                MainAppFoodManager fm = new MainAppFoodManager();
                fm.setName(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL1)));
                fm.setBrand(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL2)));
                fm.setPortion(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL3)));
                fm.setKCal(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL4)));
                fm.setCarbohydrates(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL5)));
                fm.setFat(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL6)));
                fm.setProtein(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL7)));
                fm.setMeal(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL8)));
                ar.add(fm);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return ar;
    }

    public ArrayList<MainAppFoodManager> query(String itemValue, String field){
        ArrayList<MainAppFoodManager> ar = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBMainAppContract.mainTable.TABLE_NAME + " WHERE " + field + "='" + itemValue + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                MainAppFoodManager fm = new MainAppFoodManager();
                fm.setName(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL1)));
                fm.setBrand(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL2)));
                fm.setPortion(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL3)));
                fm.setKCal(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL4)));
                fm.setCarbohydrates(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL5)));
                fm.setFat(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL6)));
                fm.setProtein(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL7)));
                fm.setMeal(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL8)));
                ar.add(fm);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return ar;
    }

    public MainAppFoodManager query(String name, String brand, String meal){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        MainAppFoodManager fm = new MainAppFoodManager();
        String sql = "SELECT * FROM " + DBMainAppContract.mainTable.TABLE_NAME + " WHERE " + DBMainAppContract.mainTable.COLUMN_NAME_COL1 + "='" + name + "' and " + DBMainAppContract.mainTable.COLUMN_NAME_COL2 + "='" + brand + "' and " + DBMainAppContract.mainTable.COLUMN_NAME_COL8 + "='" + meal + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            fm.setName(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL1)));
            fm.setBrand(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL2)));
            fm.setPortion(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL3)));
            fm.setKCal(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL4)));
            fm.setCarbohydrates(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL5)));
            fm.setFat(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL6)));
            fm.setProtein(cursor.getInt(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL7)));
            fm.setMeal(cursor.getString(cursor.getColumnIndex(DBMainAppContract.mainTable.COLUMN_NAME_COL8)));
        }
        cursor.close();
        return fm;
    }

    public int getTotal(String field){
        ArrayList<MainAppFoodManager> ar = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBMainAppContract.mainTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        int res = 0;
        if (cursor.moveToFirst()){
            do {
                res += cursor.getInt(cursor.getColumnIndex(field));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return res;
    }
}
