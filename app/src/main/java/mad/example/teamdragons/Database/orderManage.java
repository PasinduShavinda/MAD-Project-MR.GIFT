package mad.example.teamdragons.Database;

import android.provider.BaseColumns;

public final class orderManage {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private orderManage() {}

    /* Inner class that defines the table contents */
    public static class orders implements BaseColumns {
        public static final String TABLE_NAME = "myOrders";
        public static final String COLUMN_NAME_CUSTOMER_NAME = "customerName";
        public static final String COLUMN_NAME_CONTACT = "contactNo";
        public static final String COLUMN_NAME_ADDRESS= "address";
        public static final String COLUMN_NAME_POSTAL= "postalCode";
        public static final String COLUMN_NAME_DATE = "Date";
        public static final String COLUMN_NAME_WRAP = "wrappingType";
        public static final String COLUMN_NAME_PAYMENT= "paymentType";


    }
}