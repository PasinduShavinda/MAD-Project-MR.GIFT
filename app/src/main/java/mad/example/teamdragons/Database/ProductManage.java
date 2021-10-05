package mad.example.teamdragons.Database;


import android.provider.BaseColumns;

public final class ProductManage {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ProductManage() {}

    /* Inner class that defines the table contents */
    public static class Products implements BaseColumns {
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_1 = "productName";
        public static final String COLUMN_2 = "description";
        public static final String COLUMN_3 = "price";
        public static final String COLUMN_4 = "image";


    }
}
