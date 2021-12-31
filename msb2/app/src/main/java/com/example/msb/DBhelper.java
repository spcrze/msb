package com.example.msb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context,"RegisterPolice.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase msbDB) {
        msbDB.execSQL("create Table police(policeID text primary key,policeStation text,policeAddress text,policeContact text,policePassword text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase msbDB, int oldVersion, int newVersion) {
        msbDB.execSQL("drop table if exists police");

    }
    // creating method to check if user is registered or not
    public  Boolean insertPoliceService(String policeID,String policeStation,String policeAddress,String policeContact,String policePassword){
        SQLiteDatabase msbDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("policeID",policeID);
        contentValues.put("policePassword",policePassword);
        contentValues.put("policeAddress",policeAddress);
        contentValues.put("policeContact",policeContact);
        contentValues.put("policeStation",policeStation);
        long result = msbDB.insert("police",null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public  Boolean updatePoliceService(String policeID,String policeStation,String policeAddress,String policeContact,String policePassword){
        SQLiteDatabase msbDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("policePassword",policePassword);
        contentValues.put("policeAddress",policeAddress);
        contentValues.put("policeContact",policeContact);
        contentValues.put("policeStation",policeStation);
        Cursor cursor = msbDB.rawQuery("Select * from police where policeID = ?",new String[] {policeID});
        if(cursor.getCount() > 0){
            long result = msbDB.update("police",contentValues,"policeId = ?",new String[] {policeID});
            if(result == -1){
                return false;
            }
            else {
                return  true;
            }
        }
        else {
            return false;
        }
    }

    public Boolean deletePoliceService(String policeID){
        SQLiteDatabase msbDB = getWritableDatabase();
        Cursor cursor = msbDB.rawQuery("select * from police where policeID = ?",new String[] {policeID});
        if(cursor.getCount() > 0){
            long result = msbDB.delete("police","policeID = ?",new String[]{policeID});
            if(result == -1){
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }

    }

    public Cursor viewPoliceService(){
        SQLiteDatabase msbDB = this.getWritableDatabase();
        Cursor cursor = msbDB.rawQuery("Select * from police",null);
        return cursor;
    }

    // Method to check weather user exists in DN
    public Boolean checkPoliceService(String policeID){
        SQLiteDatabase msbDB = getWritableDatabase();
        Cursor cursor = msbDB.rawQuery("select * from police where policeID = ?",new String[] {policeID});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }

    }

    public Boolean checkPoliceIdPassword(String policeID,String policePassword){
        SQLiteDatabase msbDB = getWritableDatabase();
        Cursor cursor = msbDB.rawQuery("select * from police where policeID = ? and policePassword = ?",new String[]{policeID,policePassword});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }

    }

}
