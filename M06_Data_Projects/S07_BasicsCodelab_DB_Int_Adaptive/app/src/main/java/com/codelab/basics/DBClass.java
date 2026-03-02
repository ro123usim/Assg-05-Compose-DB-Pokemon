package com.codelab.basics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBClass extends SQLiteOpenHelper implements DB_Interface {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "DB_Name.db";

    private static final String TABLE_NAME = "pokemon";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "power_level INTEGER, " +
                    "description TEXT, " +
                    "access_count INTEGER)";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_TABLE);

        db.execSQL("INSERT INTO pokemon(name,power_level,description,access_count) VALUES('Bulbasaur',50,'Grass starter',0)");
        db.execSQL("INSERT INTO pokemon(name,power_level,description,access_count) VALUES('Charmander',55,'Fire starter',0)");
        db.execSQL("INSERT INTO pokemon(name,power_level,description,access_count) VALUES('Squirtle',48,'Water starter',0)");
        db.execSQL("INSERT INTO pokemon(name,power_level,description,access_count) VALUES('Pikachu',60,'Electric mouse',0)");
        db.execSQL("INSERT INTO pokemon(name,power_level,description,access_count) VALUES('Jigglypuff',45,'Singing fairy',0)");
        db.execSQL("INSERT INTO pokemon(name,power_level,description,access_count) VALUES('Gengar',80,'Ghost poison',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public int count() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    @Override
    public int save(DataModel dataModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", dataModel.getName());
        values.put("power_level", dataModel.getPowerLevel());
        values.put("description", dataModel.getDescription());
        values.put("access_count", dataModel.getAccessCount());

        db.insert(TABLE_NAME, null, values);
        db.close();
        return 0;
    }

    @Override
    public int update(DataModel dataModel) { return 0; }

    @Override
    public int deleteById(Long id) { return 0; }

    @Override
    public List<DataModel> findAll() {

        List<DataModel> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                DataModel item = new DataModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                );
                list.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    @Override
    public String getNameById(Long id) { return null; }

    @Override
    public DataModel getMax() { return null; }

    @Override
    public void incAccessCount(long id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME +
                " SET access_count = access_count + 1 WHERE id=" + id);
        db.close();
    }

    @Override
    public long getMostAccessed() {

        long mostID = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id FROM " + TABLE_NAME +
                        " ORDER BY access_count DESC LIMIT 1",
                null);

        if (cursor.moveToFirst()) {
            mostID = cursor.getLong(0);
        }

        cursor.close();
        db.close();
        return mostID;
    }
}