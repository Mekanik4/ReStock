//package com.example.restock;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.restock.objects.Item;
//
//import java.util.ArrayList;
//
//public class DatabaseHandler extends SQLiteOpenHelper {
//    private SQLiteDatabase db;
//    public static final String dbName = "databases/appdb.db";
//    public static String dbPath = "";
//    private Context mContext;
//
//    public Item findItem(String name){
//        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
//                COLUMN_PRODUCTNAME + " = '" + name + "'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Item item = new Item();
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            item.setName(cursor.getString(1));
//            item.setPrice(Double.parseDouble(cursor.getString(2)));
//            cursor.close();
//        } else {
//            item = null;
//        }
//        db.close();
//        return item;
//    }
//
//    public ArrayList<Item> readProducts() {
//        // on below line we are creating a
//        // database for reading our database.
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // on below line we are creating a cursor with query to read data from database.
//        Cursor cursorItems = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
//
//        // on below line we are creating a new array list.
//        ArrayList<Item> itemArrayList = new ArrayList<>();
//
//        // moving our cursor to first position.
//        if (cursorItems.moveToFirst()) {
//            do {
//                // on below line we are adding the data from cursor to our array list.
//                itemArrayList.add(new Item(cursorItems.getString(2),
//                        cursorItems.getDouble(4), 0));
//            } while (cursorItems.moveToNext());
//            // moving our cursor to next.
//        }
//        // at last closing our cursor
//        // and returning our array list.
//        cursorItems.close();
//        return itemArrayList;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
