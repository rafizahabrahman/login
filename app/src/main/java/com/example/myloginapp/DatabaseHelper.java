package com.example.myloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    //Table Name
    private static final String TABLE_NAME = "user";

    //User Table Columns Name
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //SQL Query Create Table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    //SQL Query Drop Table
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    //Constructor
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table user if exists
        db.execSQL(DROP_USER_TABLE);

        //Create tables  again
        onCreate(db);
    }

    //Create User record
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getUsername());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        //Insert data in a row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Check user exist or not
    public boolean checkUser(String email){

        String[] columns = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();

        //Select criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        //Selection arguments
        String[] selectionArgs = {email};

        //SQL Query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'fizdanae2012@gmail.com';
         */

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;

        }else{
            return false;
        }

    }

    //Check user exist or not
    public boolean checkUser(String email, String password){

        String[] columns = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();

        //Select criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        //Selection arguments
        String[] selectionArgs = {email, password};

        //SQL Query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'fizdanae2012@gmail.com' AND user_password = '12345';
         */

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;

        }else{
            return  false;
        }

    }




}
