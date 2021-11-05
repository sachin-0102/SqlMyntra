package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String TRANSACTION_TABLE = "TRANSACTION_TABLE";
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_PRICE = "ITEM_PRICE";
    public static final String ID = "ID";
    public static final String PURCHASE_DATE = "PURCHASE_DATE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "transaction.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + TRANSACTION_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_NAME + " TEXT, " + ITEM_PRICE + " INTEGER, " + PURCHASE_DATE + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(TransactionModel transactionModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ITEM_NAME, transactionModel.getItemName());
        cv.put(ITEM_PRICE, transactionModel.getAmount());
        cv.put(PURCHASE_DATE, transactionModel.getDate());

        long insert = db.insert(TRANSACTION_TABLE, null, cv);
        return (insert>0);
    }

    public List<TransactionModel> getTransactions(){
        List<TransactionModel> transactions = new ArrayList<>();
        String queryString = "SELECT * FROM " + TRANSACTION_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int transactionId = cursor.getInt(0);
                String itemName = cursor.getString(1);
                int itemPrice = cursor.getInt(2);
                String date = cursor.getString(3);

                TransactionModel newTransaction = new TransactionModel(itemName,transactionId,itemPrice,date);
                transactions.add(newTransaction);
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();

        return transactions;
    }
    public int last30days(){
        String queryString = "SELECT * FROM " + TRANSACTION_TABLE +" WHERE PURCHASE_DATE < date('now','-30 days')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        int sum=0;
        if(cursor.moveToFirst()){
            do{
                int itemPrice = cursor.getInt(2);
                sum+=itemPrice;
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return sum;
    }
    public int last90days(){
        String queryString = "SELECT * FROM " + TRANSACTION_TABLE +" WHERE PURCHASE_DATE < date('now','-90 days')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        int sum=0;
        if(cursor.moveToFirst()){
            do{
                int itemPrice = cursor.getInt(2);
                sum+=itemPrice;
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return sum;
    }
    public int last180days(){
        String queryString = "SELECT * FROM " + TRANSACTION_TABLE +" WHERE PURCHASE_DATE < date('now','-180 days')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        int sum=0;
        if(cursor.moveToFirst()){
            do{
                int itemPrice = cursor.getInt(2);
                sum+=itemPrice;
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return sum;
    }
    public int last365days(){
        String queryString = "SELECT * FROM " + TRANSACTION_TABLE +" WHERE PURCHASE_DATE < date('now','-365 days')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        int sum=0;
        if(cursor.moveToFirst()){
            do{
                int itemPrice = cursor.getInt(2);
                sum+=itemPrice;
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return sum;
    }
    public int currentYear(){
        String queryString = "SELECT * FROM " + TRANSACTION_TABLE +" WHERE PURCHASE_DATE < date('now','start of year')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        int sum=0;
        if(cursor.moveToFirst()){
            do{
                int itemPrice = cursor.getInt(2);
                sum+=itemPrice;
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return sum;
    }
}
