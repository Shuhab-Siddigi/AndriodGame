package dk.dtu.mitprojektarkiv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteDataBase extends SQLiteOpenHelper {

    private static final String TAG = "SQLite DATA BASE";
    // Data base version
    private static final int DATABASE_VERSION = 1;
    // Data base name
    private static final String DATABASE_NAME = "High Score's";
    // Data base tabel name (MUST BE IN ONE WORD!!!!! )
    private static final String TABLE_NAME = "SQLDATA";
    // Table Fields
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_PlAYER = "PLAYER";
    private static final String COLUMN_POINTS = "POINTS";

    SQLiteDatabase sqLiteDatabase;

    public SqlLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Don't know why but had an error if it was not written this way
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PlAYER + " TEXT, " +
                        COLUMN_POINTS + " INTEGER " +")");
        Log.i(TAG, "Executing Sql lite Base");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
        Log.i(TAG, "Creating SQlite Data Table");
    }

    public boolean insertPlayerName(String player) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PlAYER, player);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        Log.i(TAG, "Inserted data to the base");
        if (result == -1)
            return false;
        else
            return true;

    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        Log.i(TAG, "Retrieving all data from the base");
        return res;

    }

    public boolean updateNameAndId(String id, String player) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_PlAYER, player);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        Log.i(TAG, "Player " +id + "Has been updated ");
        return true;
    }

    public boolean updatePoints(String id,String points) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_POINTS,points);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public int deletePlayer (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(TAG, "Player " + id + "has been deleted");
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }


}
