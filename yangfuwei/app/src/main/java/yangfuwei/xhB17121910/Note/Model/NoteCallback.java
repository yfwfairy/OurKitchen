package yangfuwei.xhB17121910.Note.Model;

import android.database.Cursor;
import android.provider.BaseColumns;

import yangfuwei.xhB17121910.Common.ColumnContacts;
import yangfuwei.xhB17121910.Common.DBCallback;

public class NoteCallback implements DBCallback<NoteModel> {
    @Override
    public NoteModel cursorToInstance(Cursor cursor) {
        NoteModel model = new NoteModel();
        model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
        model.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_TITLE_COLUMN)));
        model.setAuther(cursor.getString(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_AUTHOR_COLUMN)));
        model.setContent(cursor.getString(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_CONTENT_COLUMN)));
        model.setTime(cursor.getLong(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_TIME_COLUMN)));
        model.setImportance(cursor.getInt(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_IMPORTANCE_COLUMN)));
        model.setType(cursor.getInt(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_TYPE_COLUMN)));
        model.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(ColumnContacts.NOTE_IMAGE_COLUMN)));
        return model;
    }
}
