package com.example.health.manager.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static DBHelper mInstance = null;

    public static DBHelper getInstance(Context ctx){
        if(mInstance == null) {
            mInstance = new DBHelper(ctx);
        }
        return mInstance;
    }

    private DBHelper(Context ctx){
        super(ctx, DBContract.DATABASE_NAME, null, DBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.TaulaAliments.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContract.TaulaAliments.DELETE_TABLE);
        onCreate(db);
    }
}
