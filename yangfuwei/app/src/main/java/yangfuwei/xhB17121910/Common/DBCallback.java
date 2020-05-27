package yangfuwei.xhB17121910.Common;

import android.database.Cursor;

public interface DBCallback<T> {
    /**
     * Gets a instance of T by cursor
     */
    T cursorToInstance(Cursor cursor);
}
