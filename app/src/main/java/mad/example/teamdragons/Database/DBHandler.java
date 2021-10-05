package mad.example.teamdragons.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "gift.db";

    public DBHandler(Context context) {
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

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + orderManage.orders.TABLE_NAME + " (" +
                    orderManage.orders._ID + " INTEGER PRIMARY KEY," +
                    orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " TEXT," +
                    orderManage.orders.COLUMN_NAME_CONTACT + " TEXT," +
                    orderManage.orders.COLUMN_NAME_ADDRESS + " TEXT," +
                    orderManage.orders.COLUMN_NAME_POSTAL + " TEXT," +
                    orderManage.orders.COLUMN_NAME_DATE + " TEXT," +
                    orderManage.orders.COLUMN_NAME_WRAP + " TEXT," +
                    orderManage.orders.COLUMN_NAME_PAYMENT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + orderManage.orders.TABLE_NAME;

    // Add data to the database
    public long addData(String customer, String contact, String address, String postal, String date, String wrap, String payment) {

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(orderManage.orders.COLUMN_NAME_CUSTOMER_NAME, customer);
        values.put(orderManage.orders.COLUMN_NAME_CONTACT, contact);
        values.put(orderManage.orders.COLUMN_NAME_ADDRESS, address);
        values.put(orderManage.orders.COLUMN_NAME_POSTAL, postal);
        values.put(orderManage.orders.COLUMN_NAME_DATE, date);
        values.put(orderManage.orders.COLUMN_NAME_WRAP, wrap);
        values.put(orderManage.orders.COLUMN_NAME_PAYMENT, payment);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(orderManage.orders.TABLE_NAME, null, values);

        return newRowId;

    }


    // Update data from the database
    public Boolean updateData(String customer, String contact, String address, String postal, String date, String wrap, String payment) {


        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(orderManage.orders.COLUMN_NAME_CONTACT, contact);
        values.put(orderManage.orders.COLUMN_NAME_ADDRESS, address);
        values.put(orderManage.orders.COLUMN_NAME_POSTAL, postal);
        values.put(orderManage.orders.COLUMN_NAME_DATE, date);
        values.put(orderManage.orders.COLUMN_NAME_WRAP, wrap);
        values.put(orderManage.orders.COLUMN_NAME_PAYMENT, payment);



        // Which row to update, based on the title
        String selection = orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " LIKE ?";
        String[] selectionArgs = { customer };

        int count = db.update(
                orderManage.orders.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1){
            return true;
        }
        else{
            return false;
        }

    }

    // delete data from the database
    public void deleteData (String customer){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { customer };
        // Issue SQL statement.
        int deletedRows = db.delete(orderManage.orders.TABLE_NAME, selection, selectionArgs);


    }

    // Retrieve all data
    public ArrayList readData(){

        String customer = "shavi";

        SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                BaseColumns._ID,
                orderManage.orders.COLUMN_NAME_CUSTOMER_NAME,
                orderManage.orders.COLUMN_NAME_CONTACT,
                orderManage.orders.COLUMN_NAME_ADDRESS,
                orderManage.orders.COLUMN_NAME_POSTAL,
                orderManage.orders.COLUMN_NAME_DATE,
                orderManage.orders.COLUMN_NAME_WRAP,
                orderManage.orders.COLUMN_NAME_PAYMENT
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " = ?";
        String[] selectionArgs = { customer };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " ASC";

        Cursor cursor = db.query(
                orderManage.orders.TABLE_NAME,    // The table to query
                projection,              // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,           // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList customers = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(
            cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_CUSTOMER_NAME)
            );
            String contact = cursor.getString(
                    cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_CONTACT)
            );
            String address = cursor.getString(
                    cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_ADDRESS)
            );
            String postal = cursor.getString(
                    cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_POSTAL)
            );
            String wrap = cursor.getString(
                    cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_WRAP)
            );
            String payment = cursor.getString(
                    cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_PAYMENT)
            );
            String date = cursor.getString(
                    cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_DATE)
            );



            customers.add(name);
            customers.add(contact);
            customers.add(address);
            customers.add(postal);
            customers.add(wrap);
            customers.add(payment);
            customers.add(date);
        }

        cursor.close();
        return customers;

    }


    public List readAllData(String customer){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                orderManage.orders.COLUMN_NAME_CUSTOMER_NAME,
                orderManage.orders.COLUMN_NAME_CONTACT,
                orderManage.orders.COLUMN_NAME_ADDRESS,
                orderManage.orders.COLUMN_NAME_POSTAL,
                orderManage.orders.COLUMN_NAME_DATE,
                orderManage.orders.COLUMN_NAME_WRAP,
                orderManage.orders.COLUMN_NAME_PAYMENT,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " LIKE ?";
        String[] selectionArgs = { customer };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                orderManage.orders.COLUMN_NAME_CUSTOMER_NAME + " ASC";

        Cursor cursor = db.query(
                orderManage.orders.TABLE_NAME,    // The table to query
                projection,              // The array of columns to return (pass null to get all)
                selection,               // The columns for the WHERE clause
                selectionArgs,           // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortOrder               // The sort order
        );

        List orderInfo = new ArrayList<>();
        while(cursor.moveToNext()) {

            String order = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_CUSTOMER_NAME));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_CONTACT));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_ADDRESS));
            String postal = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_POSTAL));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_DATE));
            String wrap = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_WRAP));
            String payment = cursor.getString(cursor.getColumnIndexOrThrow(orderManage.orders.COLUMN_NAME_PAYMENT));


            orderInfo.add(order);//0
            orderInfo.add(contact);//1
            orderInfo.add(address);//2
            orderInfo.add(postal);//3
            orderInfo.add(date);//4
            orderInfo.add(wrap);//5
            orderInfo.add(payment);//6


        }

        cursor.close();
        return orderInfo;

    }


}


