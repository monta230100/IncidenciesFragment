package com.example.incidenciesfragment.IncidenciaDBHelper;

import android.provider.BaseColumns;

public class IncidenciaContract {
    private IncidenciaContract(){}
    public static class IncidenciaEntry implements BaseColumns {
        public static final String TABLE_NAME ="incidencia";
        public static final String ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_PRIORITAT = "prioritat";
        public static final String COLUMN_DESCRIPCION = "descripcion";
        public static final String COLUMN_ESTAT = "estat";
        public static final String COLUMN_DATE = "date";
    }

}
