package mad.example.teamdragons.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;


public class DBHandlerProduct extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gift.db";

    public DBHandlerProduct(Context context) {
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
            "CREATE TABLE " + ProductManage.Products.TABLE_NAME + " (" +
                    ProductManage.Products._ID + " INTEGER PRIMARY KEY," +
                    ProductManage.Products.COLUMN_1 + " TEXT," +
                    ProductManage.Products.COLUMN_2 + " TEXT," +
                    ProductManage.Products.COLUMN_3 + " TEXT," +
                    ProductManage.Products.COLUMN_4 + " BLOB)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductManage.Products.TABLE_NAME;

    public long addInfo(byte[] bytesImage, String productName, String description, String price){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ProductManage.Products.COLUMN_1, productName);
        values.put(ProductManage.Products.COLUMN_2, description);
        values.put(ProductManage.Products.COLUMN_3, price);
        values.put(ProductManage.Products.COLUMN_4, bytesImage);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProductManage.Products.TABLE_NAME, null, values);
        return newRowId;
    }


    public boolean updateInfo(String productName, String description, String price){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column

        ContentValues values = new ContentValues();
        values.put(ProductManage.Products.COLUMN_1, productName);
        values.put(ProductManage.Products.COLUMN_2, description);
        values.put(ProductManage.Products.COLUMN_3, price);

//

// Which row to update, based on the title
        String selection = ProductManage.Products.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { productName };

        int count = db.update(
                ProductManage.Products.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count >=1){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(sql,null);
    }

    public boolean updateByName(int id, String name,String des,String price)
    {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ProductManage.Products.COLUMN_1,name);
        contentValues.put(ProductManage.Products.COLUMN_2,des);
        contentValues.put(ProductManage.Products.COLUMN_3,price);
//        contentValues.put(ProductManage.Products.COLUMN_4, bytesImage);



        String select = ProductManage.Products._ID+" Like ?";

        String[] selctionArgs = {String.valueOf(id)};

        int result = database.update(ProductManage.Products.TABLE_NAME,contentValues,select,selctionArgs);

        if(result > 0)
        {

            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean deleteById(int id) {

        SQLiteDatabase db = getReadableDatabase();

        String select  = ProductManage.Products._ID+" Like ?";

        String[] selectionargs = {String.valueOf(id)};

        int result = db.delete(ProductManage.Products.TABLE_NAME,select,selectionargs);

        if(result>0)
        {
            return true;
        }
        else
        {
            return false;
        }



    }

    public void deleteInfo (String productName){

        SQLiteDatabase db = getWritableDatabase();


        // Define 'where' part of query.
        String selection = ProductManage.Products.COLUMN_1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { productName };
// Issue SQL statement.
        int deletedRows = db.delete(ProductManage.Products.TABLE_NAME, selection, selectionArgs);
    }

    public ArrayList readInfo(){
        String productName = "P01";
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ProductManage.Products.COLUMN_1,
                ProductManage.Products.COLUMN_2,
                ProductManage.Products.COLUMN_3,
                ProductManage.Products.COLUMN_4,

        };

// Filter results WHERE "title" = 'My Title'
        String selection = ProductManage.Products.COLUMN_1 + " = ?";
        String[] selectionArgs = { productName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                ProductManage.Products.COLUMN_1 + " DESC";

        Cursor cursor = db.query(
                ProductManage.Products.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
               null,              // The columns for the WHERE clause
                null ,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList products = new ArrayList<>();
        while(cursor.moveToNext()) {
            String p = cursor.getString(cursor.getColumnIndexOrThrow(ProductManage.Products.COLUMN_1));
            products.add(p);
        }
        cursor.close();
        return products;
    }

    public List readAllInfo(String productName){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ProductManage.Products.COLUMN_1,
                ProductManage.Products.COLUMN_2,
                ProductManage.Products.COLUMN_3,

        };

// Filter results WHERE "title" = 'My Title'
        String selection = ProductManage.Products.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { productName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                ProductManage.Products.COLUMN_1 + " DESC";

        Cursor cursor = db.query(
                ProductManage.Products.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs, // ,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List productsInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String product = cursor.getString(cursor.getColumnIndexOrThrow(ProductManage.Products.COLUMN_1));
            String des = cursor.getString(cursor.getColumnIndexOrThrow(ProductManage.Products.COLUMN_2));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(ProductManage.Products.COLUMN_3));

            productsInfo.add(product);//0
            productsInfo.add(des);//1
            productsInfo.add(price);//2

        }
        cursor.close();
        return productsInfo;
    }


}
