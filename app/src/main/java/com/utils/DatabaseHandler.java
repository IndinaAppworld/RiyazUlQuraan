package com.utils;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	 
    // Database Name
    private static final String DATABASE_NAME = "riyazulquraan";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "boomark";
    private static final String TABLE_FAV = "favourite";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "rowid";
    private static final String KEY_PAGENO = "pageno";
    private static final String KEY_TITLE = "title";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
        		+KEY_PAGENO + " TEXT,"
                + KEY_TITLE + " TEXT" + ")";
        
        String CREATE_CONTACTS_FAV = "CREATE TABLE " + TABLE_FAV + "("
        		+KEY_PAGENO + " TEXT,"
                + KEY_TITLE + " TEXT" + ")";
        
        
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_FAV);
        
        
        //db = new DatabaseHandler(SubMenu.this);
        
        
        
        addData();
        
//        COnten
        
        
//        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 public void addData()
 {
	 
//	 DatabaseHandler db=new DatabaseHandler(t);
	 
//	 addContact_Fav(new Contact("", "Al-Fatiha |  سورة الفاتحة   |2","Al-Fatiha |  سورة الفاتحة   |2"));
//     addContact_Fav(new Contact("", "Al-Kahf |  سورة الكهف   |294","Al-Kahf |  سورة الكهف   |294"));
//     addContact_Fav(new Contact("", "Al-Mulk |  سورة الملك   |563","Al-Mulk |  سورة الملك   |563"));
//     addContact_Fav(new Contact("", "Al-Mulk |  سورة الملك   |563","Al-Mulk |  سورة الملك   |563"));
     
 }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_PAGENO, contact.PAGENO); // Contact Name
        values.put(KEY_TITLE, contact.TITLE); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
        
        
        
    }
 
 
    public void addContact_Fav(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_PAGENO, contact.PAGENO); // Contact Name
        values.put(KEY_TITLE, contact.TITLE); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_FAV, null, values);
        db.close(); // Closing database connection
        
        
        
    }   
    
    public ArrayList<Book> getAllContacts_Fav() {
    	ArrayList<Book> contactList = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  rowid,* FROM " + TABLE_FAV+" order by rowid desc";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	
            	String h[]=cursor.getString(1).split("\\|");
                Book contact = new Book(h[0],h[1],h[2]);//cursor.getString(0),cursor.getString(1),cursor.getString(2));
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    
    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  rowid,* FROM " + TABLE_CONTACTS+" order by rowid desc  limit 80";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(cursor.getString(0),cursor.getString(1),cursor.getString(2));
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
 
    // Deleting single contact
    public void deleteContact(String ROWID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { ROWID});
        db.close();
    }
    
    public void deleteExistingBookmark(String pageNO)
    {
////        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
////        		+KEY_PAGENO + " TEXT,"
////                + KEY_TITLE + " TEXT" + ")";
//        
//        
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_PAGENO + " = ?",
                new String[] { pageNO});
        db.close();
    }
    public void deleteExistingBookmarkByName(String KEY_TITLE1)
    {
////        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
////        		+KEY_PAGENO + " TEXT,"
////                + KEY_TITLE + " TEXT" + ")";
//        
//        
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_TITLE + " = ?",
                new String[] { KEY_TITLE1});
        db.close();
    }
    
    public void deleteContact_Fav(String ROWID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FAV, KEY_PAGENO + " = ?",
                new String[] { ROWID});
        db.close();
    }
 
    // Getting contacts Count
    public int getContactsCount(String no) {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS+" where "+KEY_PAGENO+" = '"+no+"'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 int count=cursor.getCount();
        cursor.close();
 
        // return count
        return count;
    }
//    public int getContactsCountByName(String no) {
//        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS+" where "+KEY_PAGENO+" = '"+no+"'";
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
// int count=cursor.getCount();
//        cursor.close();
// 
//        // return count
//        return count;
//    }
    public int getContactsCount_Fav(String no) {
        String countQuery = "SELECT  * FROM " + TABLE_FAV+" where "+KEY_PAGENO+" = '"+no+"'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 int count=cursor.getCount();
        cursor.close();
 
        // return count
        return count;
    }
 

}
