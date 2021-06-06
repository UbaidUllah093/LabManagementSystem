package com.maasihaa.labmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lab.db";
    public static final String Customer_TABLE_NAME = "Customer";
    public static final String Test_TABLE_NAME = "Test";
    public static final String Reports_TABLE_NAME = "Report";
    public static final String Customer_COLUMN_Name = "CustomerName";
    public static final String Customer_COLUMN_Number = "Number";
    public static final String Customer_COLUMN_CNIC = "CNIC";
    public static final String Customer_COLUMN_Address = "Address";
    public static final String Customer_COLUMN_Age = "Age";
    public static final String Customer_COLUMN_Gender = "Gender";
    public static final String Test_COLUMN_Name = "TestName";
    public static final String Test_COLUMN_Price = "Price";
    public static final String Test_COLUMN_Time = "Time";
    public static final String Reports_COLUMN_Name = "ReportName";
    public static final String Reports_COLUMN_Time = "Time";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Customer " +
                        "(id integer primary key, CustomerName text,Number text,CNIC text,Address text,Age text,Gender text)"
        );
        db.execSQL(
                "create table Test " +
                        "(id integer primary key, TestName text,Price text,Time text)"
        );
        db.execSQL(
                "create table Report " +
                        "(id integer primary key, ReportName text,Time text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS book");
        onCreate(db);
    }

    public boolean Addnewcustomer (String CustomerName,String Number,String CNIC,String Address , String Age, String Gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CustomerName", CustomerName);
        contentValues.put("Number", Number);
        contentValues.put("CNIC", CNIC);
        contentValues.put("Address", Address );
        contentValues.put("Age", Age );
        contentValues.put("Gender", Gender );

        db.insert("Customer", null, contentValues);
        return true;
    }

    public boolean addNewTest (String testName,String testPrice,String testTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TestName", testName);
        contentValues.put("Price", testPrice);
        contentValues.put("Time", testTime);

        db.insert("Test", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from book where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        //int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        //return numRows;
        return 0;
    }


    public Integer deleteBooks (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("book",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getCustomers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Customer", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("CustomerName")));// + "     Number:  " + res.getString(res.getColumnIndex("Number")));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getTests() {

        //ArrayList<ArrayList<String>> mainArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> array_list = new ArrayList<String>();
        //mainArrayList.add(array_list);

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Test", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            //array_list.add(res);
            //array_list.clear();
            //array_list.add(res.getString(res.getColumnIndex("TestName")));
            //array_list.add(res.getString(res.getColumnIndex("Price")));
            //array_list.add(res.getString(res.getColumnIndex("Time")));
            //mainArrayList.add(array_list);
            res.moveToNext();
        }
        return res;
    }

}