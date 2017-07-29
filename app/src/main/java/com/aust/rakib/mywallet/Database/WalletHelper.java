package com.aust.rakib.mywallet.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Personal on 6/18/2017.
 */

public class WalletHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="wallet_db";
    public static final int    DATABASE_VERSION = 30;
    public static final String TABLE_NAME="wallet_table";
    public static final String BACKUP_TABLE_NAME="backup_table";//////////////////////
    public static final String WALLET_ID="walltet_id";
    public static final String IMAGE="image";
    public static final String AMOUNT ="amount";
    public static final String DESCRIPTION="description";
    public static final String DATE="date";
    public static final String STATUS="status";

    public static final String BACKUP_INFORMATION_TABLE_NAME="backup_information_table";//////////////////////
    public static final String BACKUP_INFORMATION_ID="backupid";
    public static final String BACKUP_INFORMATION_KEY="backupkey";
    public static final String BACKUP_INFORMATION_FROM_DATE="fromdate";
    public static final String BACKUP_INFORMATION_TO_DATE="todate";
    public static final String BACKUP_INFORMATION_TOTAL_INCOME="total_income";
    public static final String BACKUP_INFORMATION_TOTAL_EXPENSE="total_expense";
    public static final String BACKUP_INFORMATION_TOTAL="total";


    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+ "(" +WALLET_ID+ " INTEGER PRIMARY KEY, " +IMAGE+ " INT, " +AMOUNT+ " INT, " +DESCRIPTION+ " TEXT, " +DATE+ " TEXT, " +STATUS+ " INT  DEFAULT 0);";

    public static final String BACKUP_TABLE="CREATE TABLE "+BACKUP_TABLE_NAME+ "(" +WALLET_ID+ " INTEGER PRIMARY KEY, " +IMAGE+ " INT, " +AMOUNT+ " INT, " +DESCRIPTION+ " TEXT, " +DATE+ " TEXT, " +STATUS+ " INT  DEFAULT 0);";

    public static final String BACKUP_INFORMATION_TABLE=" CREATE TABLE "+BACKUP_INFORMATION_TABLE_NAME+ "(" +BACKUP_INFORMATION_ID+ " INTEGER PRIMARY KEY, " +BACKUP_INFORMATION_FROM_DATE+ " TEXT, " +BACKUP_INFORMATION_TO_DATE+ " TEXT, " +BACKUP_INFORMATION_KEY+ " TEXT ," +BACKUP_INFORMATION_TOTAL_INCOME+ " INT ," +BACKUP_INFORMATION_TOTAL_EXPENSE+ " INT ," +BACKUP_INFORMATION_TOTAL+ " INT );";

    public WalletHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(BACKUP_TABLE);
        db.execSQL(BACKUP_INFORMATION_TABLE);
     //   db.execSQL("INSERT INTO "+TABLE_NAME+ "("+ WALLET_ID+","+AMOUNT+","+DESCRIPTION+")"+"VALUES("+"1"+","+"2"+","+"123123 );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + BACKUP_TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + BACKUP_INFORMATION_TABLE_NAME );
        onCreate(db);
    }
}
