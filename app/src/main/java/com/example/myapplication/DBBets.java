package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBBets {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private final String TABLE_NAME = "BETS";

    private final String COLUMN_ID = "_id";
    private final String COLUMN_NAME_TEAM_HOME = "Name_Team_Home";
    private final String COLUMN_NAME_TEAM_GUEST = "Name_Team_Guest";
    private final String COLUMN_BET_TEAM_HOME = "Bet_Team_Home";
    private final String COLUMN_BET_TEAM_GUEST = "Bet_Team_Guest";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TEAMHOME = 1;
    private static final int NUM_COLUMN_TEAMGUAST = 2;
    private static final int NUM_COLUMN_BETHOME = 3;
    private static final int NUM_COLUMN_BETGUEST = 4;

    private SQLiteDatabase mDataBase;

    DBBets(Context context){
        OpenHelper openHelper = new OpenHelper(context);
        mDataBase = openHelper.getWritableDatabase();
    }

    public long insert(Bet bet){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_TEAM_HOME, bet.getTeamHome());
        cv.put(COLUMN_NAME_TEAM_GUEST, bet.getTeamGuest());
        cv.put(COLUMN_BET_TEAM_HOME, bet.getBetTeamHome());
        cv.put(COLUMN_BET_TEAM_GUEST, bet.getBetTeamGuest());
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public void deleteAll(){
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(int id){
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Bet select(int id){
        Cursor cursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();

        String teamHome = cursor.getString(NUM_COLUMN_TEAMHOME);
        String teamGuest = cursor.getString(NUM_COLUMN_TEAMGUAST);
        int betTeamHome = cursor.getInt(NUM_COLUMN_BETHOME);
        int betTeamGuest = cursor.getInt(NUM_COLUMN_BETGUEST);

        return new Bet(id, teamHome, teamGuest, betTeamHome, betTeamGuest);
    }

    public  int getLastId(){
        Cursor cursor = mDataBase.query(TABLE_NAME, null, null,
                null, null, null, null);

        if(!cursor.moveToLast())
            return -1;

        return cursor.getInt(NUM_COLUMN_ID);
    }

    public ArrayList<Bet> selectAll(){
        Cursor cursor = mDataBase.query(TABLE_NAME, null, null,
                null, null, null, null);

        ArrayList<Bet> arr = new ArrayList<>();

        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            do {
                int id = cursor.getInt(NUM_COLUMN_ID);
                String teamHome = cursor.getString(NUM_COLUMN_TEAMHOME);
                String teamGuest = cursor.getString(NUM_COLUMN_TEAMGUAST);
                int betTeamHome = cursor.getInt(NUM_COLUMN_BETHOME);
                int betTeamGuest = cursor.getInt(NUM_COLUMN_BETGUEST);

                arr.add(new Bet(id, teamHome, teamGuest, betTeamHome, betTeamGuest));
            }while (cursor.moveToNext());
        }

        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_TEAM_HOME + " TEXT, " +
                    COLUMN_NAME_TEAM_GUEST + " TEXT, " +
                    COLUMN_BET_TEAM_HOME + " INTEGER, " +
                    COLUMN_BET_TEAM_GUEST + " INTEGER); ";

            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
