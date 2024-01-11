package com.example.tractorbuddy_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {

    public static final String IMAGE_ID = "id";
    public static final String IMAGE = "image";
    public static final String IMAGE_TYPE = "imagetype";
    private final Context mContext;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Images.db";
    private static final int DATABASE_VERSION = 1;

    private static final String IMAGES_TABLE = "ImagesTable";

    private static final String CREATE_IMAGES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + IMAGES_TABLE + " (" +
                    IMAGE_ID + " TEXT, "  + IMAGE_TYPE + " TEXT NOT NULL,"
                    + IMAGE + " BLOB NOT NULL );";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_IMAGES_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + IMAGES_TABLE);
            onCreate(db);
        }
    }

    public DBHelper(Context ctx) {
        mContext = ctx;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DBHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    // Insert the image to the Sqlite DB
    public void insertImage(String id, String image_type, byte[] imageBytes) {
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, imageBytes);
        cv.put(IMAGE_ID, id);
        cv.put(IMAGE_TYPE, image_type);
        mDb.insert(IMAGES_TABLE, null, cv);
    }

    public byte[] retreiveImageFromDB(String imageid, String imagetype) {
        String selection = IMAGE_ID + " = ? AND "+IMAGE_TYPE +" = ?";
        String[] selectionArgs = {imageid, imagetype};
        Cursor cur = mDb.query(false, IMAGES_TABLE, new String[]{IMAGE_ID, IMAGE_TYPE, IMAGE},
                selection, selectionArgs, null, null,
                IMAGE_ID + " DESC", "1");
        if (cur.moveToFirst()) {
            @SuppressLint("Range") byte[] blob = cur.getBlob(cur.getColumnIndex(IMAGE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }
}