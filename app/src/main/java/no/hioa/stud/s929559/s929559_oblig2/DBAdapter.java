package no.hioa.stud.s929559.s929559_oblig2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBAdapter {
    private Context context;
    private static final String TAG = "DbHelper";
    private static final String DB_NAVN = "navn.db";
    private static final String TABELL = "personer";
    private static final String ID = BaseColumns._ID;
    static final String FORNAVN = "fornavn";
    static final String ETTERNAVN = "etternavn";
    static final String BURSDAG = "bursdag";
    static final String TELEFON = "telefon";
    private static final int DB_VERSJON = 8;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DB_NAVN, null, DB_VERSJON);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table " + TABELL + " ("
                    + ID + " integer primary key autoincrement, "
                    + FORNAVN + " text, "
                    + ETTERNAVN + " text, "
                    + TELEFON + " text, "
                    + BURSDAG + " text);";
            Log.d(TAG, "oncreated sql" + sql);
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABELL);
            Log.d(TAG, "updated");
            onCreate(db);
        }
    } //slutt DatabaseHelper

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        db.close();
    }

    public void insert(ContentValues cv) {
        db.insert(TABELL, null, cv);
    }

    public boolean slett(String fornavn) {
        return db.delete(TABELL, FORNAVN + "='" + fornavn + "'", null) > 0;
    }

    public boolean oppdater(String fornavn, String etternavn) {
        ContentValues cv = new ContentValues(2);
        cv.put(FORNAVN, fornavn);
        cv.put(ETTERNAVN, etternavn);
        return db.update(TABELL, cv, ETTERNAVN + "='" + etternavn + "'", null) > 0;
    }

    public Cursor finnalle() {
        Cursor cur;
        String[] cols = {BaseColumns._ID, FORNAVN, ETTERNAVN, TELEFON, BURSDAG};
        cur = db.query(TABELL, cols, null, null, null, null, ETTERNAVN);
        return cur;
    }

    public Cursor find(String fornavn, String etternavn) {
        Cursor cur;
        String[] cols = {BaseColumns._ID, FORNAVN, ETTERNAVN, TELEFON, BURSDAG};
        cur = db.query(TABELL, cols, ETTERNAVN + "='" + etternavn + "' AND " + FORNAVN + "='" + fornavn + "'",
                null, null, null, null);
        return cur;
    }
}