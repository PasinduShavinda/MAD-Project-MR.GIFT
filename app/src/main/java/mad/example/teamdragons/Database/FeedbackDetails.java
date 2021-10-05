package mad.example.teamdragons.Database;

import android.provider.BaseColumns;

public final class FeedbackDetails {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedbackDetails() {}

        /* Inner class that defines the table contents */
        public static class Feedbacks implements BaseColumns {
            public static final String TABLE_NAME = "FeedbackInfo";
            public static final String COLUMN_NAME_Customer_Name = "customerName";
            public static final String COLUMN_NAME_Email = "email";
            public static final String COLUMN_NAME_Description = "description";
        }
    }

