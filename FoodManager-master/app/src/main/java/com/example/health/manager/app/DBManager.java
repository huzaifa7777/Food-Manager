package com.example.health.manager.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DBManager {

    private DBHelper dbHelper = null;

    public DBManager(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    // Insert method
    public void insert(FoodManager fm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL1, fm.getName());
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL2, fm.getBrand());
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL3, fm.getPortion());
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL4, fm.getKCal());
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL5, fm.getCarbohydrates());
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL6, fm.getFat());
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL7, fm.getProtein());

        db.insert(DBContract.TaulaAliments.TABLE_NAME, null, values);
    }

    public void update(String nom, String marca, int tamany, int calories, int carbohidrats, int greix, int proteines) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL1, nom);
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL2, marca);
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL3, tamany);
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL4, calories);
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL5, carbohidrats);
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL6, greix);
        values.put(DBContract.TaulaAliments.COLUMN_NAME_COL7, proteines);

        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL1 + "=?", new String[]{nom});
        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL2 + "=?", new String[]{marca});
        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL3 + "=?", new String[]{String.valueOf(tamany)});
        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL4 + "=?", new String[]{String.valueOf(calories)});
        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL5 + "=?", new String[]{String.valueOf(carbohidrats)});
        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL6 + "=?", new String[]{String.valueOf(greix)});
        db.update(DBContract.TaulaAliments.TABLE_NAME, values, DBContract.TaulaAliments.COLUMN_NAME_COL7 + "=?", new String[]{String.valueOf(proteines)});
    }

    //delete method
    public void delete(String name, String brand) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(DBContract.TaulaAliments.TABLE_NAME, DBContract.TaulaAliments.COLUMN_NAME_COL1 + "='" + name + "' and " + DBContract.TaulaAliments.COLUMN_NAME_COL2 + "='" + brand + "'", null);
        db.close();
    }

    // select methods
    public ArrayList<FoodManager> query(String searchName){
        ArrayList<FoodManager> ar = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBContract.TaulaAliments.TABLE_NAME + " WHERE " + DBContract.TaulaAliments.COLUMN_NAME_COL1 + "='" + searchName + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                FoodManager fm = new FoodManager();
                fm.setName(cursor.getString(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL1)));
                fm.setBrand(cursor.getString(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL2)));
                fm.setPortion(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL3)));
                fm.setKCal(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL4)));
                fm.setCarbohydrates(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL5)));
                fm.setFat(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL6)));
                fm.setProtein(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL7)));
                ar.add(fm);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return ar;
    }

    public FoodManager query(String nom_cercar, String marca_cercar){

        FoodManager fm = new FoodManager();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBContract.TaulaAliments.TABLE_NAME + " WHERE " + DBContract.TaulaAliments.COLUMN_NAME_COL1 + "='" + nom_cercar + "' AND " + DBContract.TaulaAliments.COLUMN_NAME_COL2 + "='" + marca_cercar + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                fm.setName(cursor.getString(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL1)));
                fm.setBrand(cursor.getString(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL2)));
                fm.setPortion(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL3)));
                fm.setKCal(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL4)));
                fm.setCarbohydrates(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL5)));
                fm.setFat(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL6)));
                fm.setProtein(cursor.getInt(cursor.getColumnIndex(DBContract.TaulaAliments.COLUMN_NAME_COL7)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return fm;
    }
}
