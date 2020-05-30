package yangfuwei.xhB17121910.Note.Model;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;

import java.util.Date;
import java.util.List;

import yangfuwei.xhB17121910.Common.ColumnContacts;
import yangfuwei.xhB17121910.Common.NoteDBTemplate;

public class NoteDao {
    private NoteDBTemplate<NoteModel> mTemplate;
    private NoteCallback mCallback = new NoteCallback();
    private static NoteDao mNoteDao = new NoteDao();
    private NoteDao() {

    }
    public static NoteDao getInstance(Context context) {
        mNoteDao.mTemplate = new NoteDBTemplate<>(context);
        return mNoteDao;
    }

    public List<NoteModel> findAll() {
        String sql = "SELECT * FROM " + ColumnContacts.NOTE_TABLE_NAME + " ORDER BY " + ColumnContacts.NOTE_IMPORTANCE_COLUMN + " DESC, " + ColumnContacts.NOTE_TIME_COLUMN + " DESC";
        return mTemplate.query(sql, mCallback);
    }

    public List<NoteModel> findByType(int type) {
        String sql = "SELECT * FROM " + ColumnContacts.NOTE_TABLE_NAME + " WHERE " + ColumnContacts.NOTE_TYPE_COLUMN + " = ?";
        return mTemplate.query(sql, mCallback, Integer.toString(type));
    }

    public NoteModel findById(Integer id) {
        String sql = "SELECT * FROM " + ColumnContacts.NOTE_TABLE_NAME + " WHERE " + BaseColumns._ID + " = ?";
        return mTemplate.queryOne(sql, mCallback, Integer.toString(id));
    }

    public int remove(List<Integer> ids) {
        StringBuilder whereConditions = new StringBuilder(BaseColumns._ID + " IN(");
        for (Integer id : ids) {
            whereConditions.append(id).append(",");
        }
        whereConditions.deleteCharAt(whereConditions.length() - 1).append(")");
        return mTemplate.remove(ColumnContacts.NOTE_TABLE_NAME, whereConditions.toString());
    }

    public int create(NoteModel note) {
        return (int) mTemplate.create(ColumnContacts.NOTE_TABLE_NAME,generateContentValues(note));
    }

    public int update(NoteModel noteModel) {
        return mTemplate.update(ColumnContacts.NOTE_TABLE_NAME, generateContentValues(noteModel), BaseColumns._ID + "  = ?", Integer.toString(noteModel.getId()));

    }


    private ContentValues generateContentValues(NoteModel event) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnContacts.NOTE_TITLE_COLUMN, event.getTitle());
        contentValues.put(ColumnContacts.NOTE_AUTHOR_COLUMN, event.getAuther());
        contentValues.put(ColumnContacts.NOTE_TIME_COLUMN, event.getTime());
        contentValues.put(ColumnContacts.NOTE_CONTENT_COLUMN, event.getContent());
        contentValues.put(ColumnContacts.NOTE_IMAGE_COLUMN, event.getImageUrl());
        contentValues.put(ColumnContacts.NOTE_IMPORTANCE_COLUMN, event.getImportance());
        contentValues.put(ColumnContacts.NOTE_TYPE_COLUMN, event.getType());
        return contentValues;
    }


}
