package com.example.android.sqlitetst.Utils;

import android.provider.BaseColumns;


public class EleveContract {
    public static final class EleveEntry implements BaseColumns{
        public static final String TABLE_NAME = "Eleve";
        public static final String COLUMN_NOM ="nom";
        public static final String COLUMN_NOTE ="note";
        public static final String COLUMN_TIMESTAMP="timestamp";
    }
}
