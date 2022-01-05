package com.example.persis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "tugas_akhir.db";
    public static final  String tableName = "classes";
    public static final  String columnId = "id";
    public static final  String columnTitle = "title";
    private  SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context,databaseName,null,2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName + "(" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + columnTitle + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public Cursor get(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+tableName,null);
        return cursor;
    }

    public Cursor find(Long id){
        Cursor cursor = db.rawQuery("SELECT * FROM "+tableName + " WHERE " + columnId + "=" + id,null);
        return cursor;
    }

    public long create(String title){
        db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(columnTitle,title);
        long result = db.insert(tableName,null,data);
        return result;
    }

    public long update(String id,String title){
        db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(columnTitle,title);
        long result = db.update(tableName, data, columnId + "=" + id, null);
        return result;

    }

    //Delete Data
    public void delete(String id){
        db.delete(tableName, columnId + "=" + id, null);
    }

    public void deleteAll(){

        db.delete(tableName,null,null);
        db.execSQL("UPDATE `sqlite_sequence` SET `seq` = 0 WHERE `name` = 'classes';");

    }
}
