package com.maasihaa.labmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
        db.execSQL(
                "create table Ordre " +
                        "(id integer primary key, CustomerID text,TestID text)"
        );
        db.execSQL(
                "create table MultiTest " +
                        "(id integer, TestID text)"
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

    public boolean addNewOrdre (String CustomerID,ArrayList<String> testPrice) {

        for (int i=0; i<testPrice.size();i++) {
            SQLiteDatabase db = this.getWritableDatabase();
            int numRows = (int) DatabaseUtils.queryNumEntries(db, "Ordre");
            ContentValues contentValues = new ContentValues();
            int iend = testPrice.get(i).indexOf("-");
            String subString = null;
            if (iend != -1)
            {
                subString= testPrice.get(i).substring(0 , iend); //this will give abc
            }

            contentValues.put("id", numRows + 1);
            contentValues.put("TestID", subString);

            db.insert("MultiTest", null, contentValues);
        }

        //db.insert("MultiTest", null, contentValues);

        SQLiteDatabase db2 = this.getWritableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db2, "Ordre");
        ContentValues contentValues2 = new ContentValues();
        int iend = CustomerID.indexOf("-");
        String subString = null;
        if (iend != -1)
        {
            subString= CustomerID.substring(0 , iend); //this will give abc
        }

        contentValues2.put("CustomerID", subString);
        contentValues2.put("TestID", numRows+1);
        db2.insert("Ordre", null, contentValues2);

        return true;
    }

    public ArrayList<String> getCustomerName(String id) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Customer where id="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("CustomerName")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getCustomerDetail(String id) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        int iend = id.indexOf("-");
        String subString = null;
        if (iend != -1)
        {
            id = id.substring(0 , iend); //this will give abc
        }

        Cursor res =  db.rawQuery( "select * from Customer where id="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("CustomerName")));
            array_list.add(res.getString(res.getColumnIndex("Number")));
            array_list.add(res.getString(res.getColumnIndex("Age")));
            array_list.add(res.getString(res.getColumnIndex("Gender")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getTestName(String id) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from MultiTest where id="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String testId = res.getString(res.getColumnIndex("TestID"));
            Cursor res2 =  db.rawQuery( "select * from Test where id="+testId+"", null );
            res2.moveToFirst();
            while(res2.isAfterLast() == false) {
                array_list.add(res2.getString(res2.getColumnIndex("TestName")));
                res2.moveToNext();
            }
            res.moveToNext();
        }
        return array_list;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        //int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        //return numRows;
        return 0;
    }


    public Integer deleteBooks (CharSequence id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Test","TestName = ? ",new String[] {String.valueOf(id)});
    }

    public Integer deletePending (CharSequence id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Ordre","TestID = ? ",new String[] {String.valueOf(id)});
    }

    public ArrayList<String> getCustomers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Customer", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("id")) + "-" + res.getString(res.getColumnIndex("CustomerName")));// + "     Number:  " + res.getString(res.getColumnIndex("Number")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getTestNames() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Test", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("id")) + "-" + res.getString(res.getColumnIndex("TestName")));// + "     Number:  " + res.getString(res.getColumnIndex("Number")));
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

    public Cursor getPendingScheduleComplete() {

        //ArrayList<ArrayList<String>> mainArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> array_list = new ArrayList<String>();
        //mainArrayList.add(array_list);

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Ordre", null );
        res.moveToFirst();

        //while(res.isAfterLast() == false){
            //array_list.add(res);
            //array_list.clear();
            //array_list.add(res.getString(res.getColumnIndex("TestName")));
            //array_list.add(res.getString(res.getColumnIndex("Price")));
            //array_list.add(res.getString(res.getColumnIndex("Time")));
            //mainArrayList.add(array_list);
            //res.moveToNext();
        //}
        return res;
    }

}