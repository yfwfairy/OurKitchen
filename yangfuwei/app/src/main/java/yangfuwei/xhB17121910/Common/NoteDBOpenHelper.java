package yangfuwei.xhB17121910.Common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class NoteDBOpenHelper extends SQLiteOpenHelper {

    private final static String DATABASENAME = "ourkitchen.db";
    private final static int DATABASEVERSION = 1;

    public NoteDBOpenHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    public NoteDBOpenHelper() {
        super(OKApplication.getContext(),DATABASENAME,null,DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*第一次初始化app，创建表结构 */
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ColumnContacts.NOTE_TABLE_NAME + "( "
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColumnContacts.NOTE_TITLE_COLUMN + " text, "
                + ColumnContacts.NOTE_CONTENT_COLUMN + " text, "
                + ColumnContacts.NOTE_TIME_COLUMN + " INTEGER, "
                + ColumnContacts.NOTE_IMPORTANCE_COLUMN + " INTEGER, "
                + ColumnContacts.NOTE_AUTHOR_COLUMN + " text, "
                + ColumnContacts.NOTE_IMAGE_COLUMN + " text,"
                + ColumnContacts.NOTE_TYPE_COLUMN + " INTEGER"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
