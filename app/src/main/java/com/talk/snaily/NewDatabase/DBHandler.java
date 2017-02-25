package com.talk.snaily.NewDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.talk.snaily.NewDatabase.Inquiry;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    Inquiry inquiry;

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "inquiryManager";

    // Contacts table name
    private static final String TABLE_INQUIRY = "my_inquiry";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_QUESTION = "question";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INQUIRY_TABLE = "CREATE TABLE " + TABLE_INQUIRY + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_QUESTION + " TEXT" + ")";
        db.execSQL(CREATE_INQUIRY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INQUIRY);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addMyInquiry(Inquiry inquiry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, inquiry.getmCourse());
        values.put(KEY_DATE, inquiry.getmDate());
        values.put(KEY_TIME, inquiry.getmTime());
        values.put(KEY_QUESTION, inquiry.getmQuestion());

        // Inserting Row
        db.insert(TABLE_INQUIRY, null, values);
        db.close();
    }


    // Getting contacts Count
    public int getMyInquiryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_INQUIRY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }

    // Getting All Contacts
    public List<Inquiry> getAllInquiries() {
        List<Inquiry> inquiryList = new ArrayList<Inquiry>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_INQUIRY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Inquiry inquiry = new Inquiry();
                inquiry.setmID(cursor.getString(0));
                inquiry.setmCourse(cursor.getString(1));
                inquiry.setmDate(cursor.getString(2));
                inquiry.setmTime(cursor.getString(3));
                inquiry.setmQuestion(cursor.getString(4));
                // Adding contact to list
                inquiryList.add(inquiry);
            } while (cursor.moveToNext());
        }

        // return contact list
        return inquiryList;
    }


    // Deleting single contact

    public void deleteInquiry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INQUIRY, KEY_ID + " = ?",new String[]{ String.valueOf(id) });
        db.close();
    }

    public boolean updateData(Inquiry inquiry, int id){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, inquiry.getmCourse());
        values.put(KEY_DATE, inquiry.getmDate());
        values.put(KEY_TIME, inquiry.getmTime());
        values.put(KEY_QUESTION, inquiry.getmQuestion());

        db.update(TABLE_INQUIRY, values, KEY_ID + " = ?", new String[]{ String.valueOf(id) });
        return true;
    }
}
