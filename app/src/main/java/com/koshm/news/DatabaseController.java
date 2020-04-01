package com.koshm.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.koshm.news.models.DBHelper;
import com.koshm.news.models.History;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.koshm.news.models.DBHelper.KEY_ID;
import static com.koshm.news.models.DBHelper.KEY_TITLE;
import static com.koshm.news.models.DBHelper.KEY_URL;
import static com.koshm.news.models.DBHelper.TABLE_HISTORY;

public class DatabaseController {

    DBHelper dbHelper;
    SQLiteDatabase database;

    public DatabaseController(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void save(String title, String url) {
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_TITLE, title);
        values.put(KEY_URL, url);

        database.insert(TABLE_HISTORY, null, values);
    }

    public ArrayList<History> getHistories() {
        Cursor cursor = database.query(TABLE_HISTORY, null, null, null, null, null, null);
        ArrayList<History> histories = new ArrayList<>();
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int titleIndex = cursor.getColumnIndex(KEY_TITLE);
            int urlIndex = cursor.getColumnIndex(KEY_URL);
            do{
                History newHistory = new History(cursor.getInt(idIndex), cursor.getString(titleIndex), cursor.getString(urlIndex));
                if(!histories.contains(newHistory)) {
                    histories.add(newHistory);
                }else{
                    deleteByID(newHistory.getId());
                }
            }while(cursor.moveToNext());
        }

        Collections.reverse(histories);
        return histories;
    }

    public void deleteByID(int id) {
        this.database.execSQL("DELETE FROM " + TABLE_HISTORY + " WHERE " + KEY_ID + " = " + id);
    }

    public void delete(){
        database.delete(TABLE_HISTORY,null, null);
    }
}
