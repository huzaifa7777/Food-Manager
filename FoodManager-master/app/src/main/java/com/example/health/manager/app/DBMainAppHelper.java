package com.example.health.manager.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMainAppHelper extends SQLiteOpenHelper{
    private static DBMainAppHelper mInstance = null;

    public static DBMainAppHelper getInstance(Context ctx){
        if(mInstance == null) {
            mInstance = new DBMainAppHelper(ctx);
        }
        return mInstance;
    }

    private DBMainAppHelper(Context ctx){
        super(ctx, DBMainAppContract.DATABASE_NAME, null, DBMainAppContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBMainAppContract.mainTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBMainAppContract.mainTable.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DBMainAppContract.mainTable.DELETE_TABLE);
        onCreate(db);
    }
}
