package mad.example.teamdragons.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerFeedback extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Database.db";

        public DBHandlerFeedback(Context context) {
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
            "CREATE TABLE " + FeedbackDetails.Feedbacks.TABLE_NAME + " (" +
                    FeedbackDetails.Feedbacks._ID + " INTEGER PRIMARY KEY," +
                    FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " TEXT," +
                    FeedbackDetails.Feedbacks.COLUMN_NAME_Email + " TEXT," +
                    FeedbackDetails.Feedbacks.COLUMN_NAME_Description + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedbackDetails.Feedbacks.TABLE_NAME;

    //Insert feedback data into the database
    public long addData(String customerName, String email, String description){


        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name, customerName);
        values.put(FeedbackDetails.Feedbacks.COLUMN_NAME_Email, email);
        values.put(FeedbackDetails.Feedbacks.COLUMN_NAME_Description, description);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedbackDetails.Feedbacks.TABLE_NAME, null, values);

        return newRowId;
}

    // Update data from the database
    public Boolean updateData(String customerName, String email, String description) {


        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedbackDetails.Feedbacks.COLUMN_NAME_Email, email);
        values.put(FeedbackDetails.Feedbacks.COLUMN_NAME_Description, description);

        // Which row to update, based on the title
        String selection = FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " LIKE ?";
        String[] selectionArgs = { customerName };

        int count = db.update(
                FeedbackDetails.Feedbacks.TABLE_NAME,
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
    public void deleteData (String customerName){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { customerName };
        // Issue SQL statement.
        int deletedRows = db.delete(FeedbackDetails.Feedbacks.TABLE_NAME, selection, selectionArgs);


    }

    // Retrieve all data
    public ArrayList readData(){

        String customerName = "supi";

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name,
                FeedbackDetails.Feedbacks.COLUMN_NAME_Email,
                FeedbackDetails.Feedbacks.COLUMN_NAME_Description,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " = ?";
        String[] selectionArgs = { customerName };

        // How you want the results sorted in the resulting Cursor
        String sortFeedback =
                FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " ASC";

        Cursor cursor = db.query(
                FeedbackDetails.Feedbacks.TABLE_NAME,    // The table to query
                projection,              // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,           // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortFeedback               // The sort order
        );

        ArrayList feedbacks = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(

                    cursor.getColumnIndexOrThrow(FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name));

            String email = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackDetails.Feedbacks.COLUMN_NAME_Email));

            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackDetails.Feedbacks.COLUMN_NAME_Description));

            feedbacks.add(name);
            feedbacks.add(email);
            feedbacks.add(description);

        }

        cursor.close();
        return feedbacks;

    }

    public List readAllData(String customerName){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name,
                FeedbackDetails.Feedbacks.COLUMN_NAME_Email,
                FeedbackDetails.Feedbacks.COLUMN_NAME_Description,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " LIKE ?";
        String[] selectionArgs = { customerName };

        // How you want the results sorted in the resulting Cursor
        String sortFeedback =
                FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name + " ASC";

        Cursor cursor = db.query(
                FeedbackDetails.Feedbacks.TABLE_NAME,    // The table to query
                projection,              // The array of columns to return (pass null to get all)
                selection,               // The columns for the WHERE clause
                selectionArgs,           // The values for the WHERE clause
                null,            // don't group the rows
                null,             // don't filter by row groups
                sortFeedback               // The sort order
        );

        List feedbackInfo = new ArrayList<>();
        while(cursor.moveToNext()) {

            String feedback = cursor.getString(cursor.getColumnIndexOrThrow(FeedbackDetails.Feedbacks.COLUMN_NAME_Customer_Name));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedbackDetails.Feedbacks.COLUMN_NAME_Email));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(FeedbackDetails.Feedbacks.COLUMN_NAME_Description));

            feedbackInfo.add(feedback);//0
            feedbackInfo.add(email);//1
            feedbackInfo.add(description);//2

        }

        cursor.close();
        return feedbackInfo;

    }


}


