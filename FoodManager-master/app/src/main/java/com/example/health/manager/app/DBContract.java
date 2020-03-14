package com.example.health.manager.app;

import android.provider.BaseColumns;

public final class DBContract {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "aliments.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private DBContract(){}

    public static abstract class TaulaAliments implements BaseColumns{
        public static final String TABLE_NAME = "aliments";
        public static final String COLUMN_NAME_COL1 = "nom";
        public static final String COLUMN_NAME_COL2 = "marca";
        public static final String COLUMN_NAME_COL3 = "tamany";
        public static final String COLUMN_NAME_COL4 = "calories";
        public static final String COLUMN_NAME_COL5 = "carbohidrats";
        public static final String COLUMN_NAME_COL6 = "greix";
        public static final String COLUMN_NAME_COL7 = "proteines";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME_COL1 + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_COL2 + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                COLUMN_NAME_COL3 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL4 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL5 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL6 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_COL7 + INTEGER_TYPE + COMMA_SEP +
                " PRIMARY KEY ( " + COLUMN_NAME_COL1 + COMMA_SEP + COLUMN_NAME_COL2 + ")" + ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
