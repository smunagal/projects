package edu.buffalo.cse.cse486586.groupmessenger1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GroupMessengerProvider is a key-value table. Once again, please note that we do not implement
 * full support for SQL as a usual ContentProvider does. We re-purpose ContentProvider's interface
 * to use it as a key-value table.
 *
 * Please read:
 *
 * http://developer.android.com/guide/topics/providers/content-providers.html
 * http://developer.android.com/reference/android/content/ContentProvider.html
 *
 * before you start to get yourself familiarized with ContentProvider.
 *
 * There are two methods you need to implement---insert() and query(). Others are optional and
 * will not be tested.
 *
 * @author stevko
 *
 */


public class GroupMessengerProvider extends ContentProvider {
    public static final String DATABASE_NAME = "GROUPTABLE.db";
    public static final String TABLE_NAME = "GROUP_TABLE";

    @Override
    public boolean onCreate() {
        // If you need to perform any one-time initialization task, please do it here.
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /*
         * TODO: You need to implement this method. Note that values will have two columns (a key
         * column and a value column) and one row that contains the actual (key, value) pair to be
         * inserted.
         * 
         * For actual storage, you can use any option. If you know how to use SQL, then you can use
         * SQLite. But this is not a requirement. You can use other storage options, such as the
         * internal storage option that we used in PA1. If you want to use that option, please
         * take a look at the code for PA1.
         */
        /**
         * References :
         * http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
         *
         */
        // Getting DB in Writable mode for Insert using DBHelper Object.

        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getWritableDatabase();

        long rowId = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        if(rowId != -1){
            Log.e("INSERTED ,values ",values.toString() );
        }

        // Commiting Changes by closing Transaction
        db.close();

        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        /*
         * TODO: You need to implement this method. Note that you need to return a Cursor object
         * with the right format. If the formatting is not correct, then it is not going to work.
         *
         * If you use SQLite, whatever is returned from SQLite is a Cursor object. However, you
         * still need to be careful because the formatting might still be incorrect.
         *
         * If you use a file storage option, then it is your job to build a Cursor * object. I
         * recommend building a MatrixCursor described at:
         * http://developer.android.com/reference/android/database/MatrixCursor.html
         */

        /**
         * Reference:
         * http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
         */

        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        // The PTestClickListener is passing KeyValue in Selection Argument instead of Selection Args
        // SO redefining the values needed for Query as below.
        // Log.e("selectionArgs ",selection);

        String[] Projection = { "key", "value" };
        String Selection = "key"+ "=?";
        String SelectionArgs[] = new String[] {selection};

        Cursor cursor = db.query(
                TABLE_NAME,     // Table name = group_table
                Projection,     // key , value columns are requested. null would also return both.
                Selection,      // SQL SYNTAX : WHERE key =
                SelectionArgs,  // argument for key column
                null,
                null,
                sortOrder );

        // TO position index for columns in cursor
         cursor.moveToFirst();

        //Log.e("Cursor count", String.valueOf(cursor.getCount()));
        //Log.e("Cursor cols",  cursor.getColumnNames()[0] + ", " +cursor.getColumnNames()[1]);
        db.close();
        return cursor;
    }


    /* Methods not implemented */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;}
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;}
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
