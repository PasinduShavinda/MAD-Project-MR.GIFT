package mad.example.teamdragons.Database;

import android.provider.BaseColumns;

public final class Payment {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Payment() {}

    /* Inner class that defines the table contents */
    public static class Pay implements BaseColumns {
        public static final String TABLE_NAME = "payment";
        public static final String COLUMN_1 = "cardNo";
        public static final String COLUMN_2 = "cashholderN";
        public static final String COLUMN_3 = "expdate";
        public static final String COLUMN_4 = "cvc";
    }
}
