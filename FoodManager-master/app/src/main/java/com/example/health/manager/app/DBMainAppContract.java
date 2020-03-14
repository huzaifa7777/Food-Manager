package com.example.health.manager.app;

import android.provider.BaseColumns;
import java.util.Calendar;

public class DBMainAppContract {

    public static final int DATABASE_VERSION = getDate();
    public static final String DATABASE_NAME = "main.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private DBMainAppContract(){}

    public static abstract class mainTable implements BaseColumns {
        public static final String TABLE_NAME = "mainTable";
        public static final String COLUMN_NAME_COL1 = "nom";
        public static final String COLUMN_NAME_COL2 = "marca";
        public static final String COLUMN_NAME_COL3 = "tamany";
        public static final String COLUMN_NAME_COL4 = "calories";
        public static final String COLUMN_NAME_COL5 = "carbohidrats";
        public static final String COLUMN_NAME_COL6 = "greix";
        public static final String COLUMN_NAME_COL7 = "proteines";
        public static final String COLUMN_NAME_COL8 = "apat";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME_COL1 + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_COL2 + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_COL3 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL4 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL5 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL6 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL7 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL8 + TEXT_TYPE + COMMA_SEP +
                " PRIMARY KEY ( " + COLUMN_NAME_COL1 + COMMA_SEP + COLUMN_NAME_COL2 + COMMA_SEP + COLUMN_NAME_COL8 + ")" + ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    private static int getDate(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) * 10000 + (c.get(Calendar.MONTH) + 1) * 100 + c.get(Calendar.DAY_OF_MONTH);
    }
}
