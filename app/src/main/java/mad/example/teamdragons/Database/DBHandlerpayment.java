package mad.example.teamdragons.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerpayment extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gift.db";

    public DBHandlerpayment(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //insert code start here
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Payment.Pay.TABLE_NAME + " (" +
                    Payment.Pay._ID + " INTEGER PRIMARY KEY," +
                    Payment.Pay.COLUMN_1 + " TEXT," +
                    Payment.Pay.COLUMN_2 + " TEXT," +
                    Payment.Pay.COLUMN_3 + " TEXT," +
                    Payment.Pay.COLUMN_4 + " TEXT)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Payment.Pay.TABLE_NAME;


    public long paymentAdd(String cardN, String cashholderN, String expired, String cvc){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Payment.Pay.COLUMN_1, cardN);
        values.put(Payment.Pay.COLUMN_2, cashholderN);
        values.put(Payment.Pay.COLUMN_3, expired);
        values.put(Payment.Pay.COLUMN_4, cvc);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Payment.Pay.TABLE_NAME, null, values);

        return newRowId;
    }


    //update code start here
    public Boolean updatePayment(String cardN, String cashholderN, String expired, String cvc){
        SQLiteDatabase db = getWritableDatabase();

    // New value for one column
        //video eke meke cashholder name eka update nokrannai dala thiyenne
        ContentValues values = new ContentValues();
        values.put(Payment.Pay.COLUMN_1, cardN);
        values.put(Payment.Pay.COLUMN_3, expired);
        values.put(Payment.Pay.COLUMN_4, cvc);

    // Which row to update, based on the title
        String selection = Payment.Pay.COLUMN_2 + " LIKE ?";
        String[] selectionArgs = { cashholderN };

        int count = db.update(
                Payment.Pay.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count>=1)
        {
            return true;
        }
        else{
            return false;
        }
    }

    //delete function
    public void deletePay(String cashholderN){
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = Payment.Pay.COLUMN_2 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { cashholderN };
        // Issue SQL statement.
        int deletedRows = db.delete(Payment.Pay.TABLE_NAME, selection, selectionArgs);
    }

    //retrive data
    public ArrayList  readAllPayment(){
        String cashholderN = "Ayesh";
        SQLiteDatabase db =getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Payment.Pay.COLUMN_1,
                Payment.Pay.COLUMN_2,
                Payment.Pay.COLUMN_3,
                Payment.Pay.COLUMN_4,
        };

    // Filter results WHERE "title" = 'My Title'
        String selection = Payment.Pay.COLUMN_2 + " = ?";
        String[] selectionArgs = { cashholderN };

    // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Payment.Pay.COLUMN_2 + " ASC";

        Cursor cursor = db.query(
                Payment.Pay.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,
                // The columns for the WHERE clause
               null ,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList  paydetails = new ArrayList<>();
        while(cursor.moveToNext()) {
            String paycustomer = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_2));
            String paycardN = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_1));
            String payExpire = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_3));
            String payCvc = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_4));

            paydetails.add(paycustomer);
            paydetails.add(paycardN);
            paydetails.add(payExpire);
            paydetails.add(payCvc);
        }
        cursor.close();
        return paydetails;
    }


    //search code start here
    public List readAllPayment(String cashholderN){

        SQLiteDatabase db =getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Payment.Pay.COLUMN_1,
                Payment.Pay.COLUMN_2,
                Payment.Pay.COLUMN_3,
                Payment.Pay.COLUMN_4,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Payment.Pay.COLUMN_2 + " LIKE ?";
        String[] selectionArgs = { cashholderN };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Payment.Pay.COLUMN_2 + " ASC";

        Cursor cursor = db.query(
                Payment.Pay.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,
                // The columns for the WHERE clause
                selectionArgs ,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList payInfo = new ArrayList<>();
        while(cursor.moveToNext()) {

            String cardN = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_1));
            String paycusName = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_2));
            String exp = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_3));
            String cvc = cursor.getString(cursor.getColumnIndexOrThrow(Payment.Pay.COLUMN_4));
            payInfo.add(cardN);//0
            payInfo.add(paycusName);//1 this index will use to get data of username (search eka implement kraddi oni wenwa)
            payInfo.add(exp);//2
            payInfo.add(cvc);//3
        }
        cursor.close();
        return payInfo;
    }



}
