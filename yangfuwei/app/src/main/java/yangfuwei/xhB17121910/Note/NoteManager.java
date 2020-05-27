package yangfuwei.xhB17121910.Note;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yangfuwei.xhB17121910.Common.OKApplication;
import yangfuwei.xhB17121910.Note.Model.NoteDao;
import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.Utils.StringUtils;
import yangfuwei.xhB17121910.Utils.ToastUtils;


public class NoteManager {
    private static final String TAG = NoteManager.class.getSimpleName();
    private static NoteManager mNoteManager = new NoteManager();
    private NoteDao mNoteDao;

    public List<NoteModel> notes = new ArrayList<>();

    public static NoteManager getInstance() {
        mNoteManager.mNoteDao = NoteDao.getInstance(OKApplication.getContext());
        return mNoteManager;
    }

    public List<NoteModel> findAll() {
        notes = mNoteDao.findAll();
        return notes;
    }

    public void refreshData() {
        notes = mNoteDao.findAll();
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public int removeNotes(final List<Integer> ids) {
        return mNoteDao.remove(ids);
    }

    public boolean removeNote(int id) {
        return mNoteDao.remove(Collections.singletonList(id)) != 0;
    }

    public boolean saveOrUpdate(NoteModel model) {
        try {
            if (mNoteDao.findById(model.getId()) != null) {
                mNoteDao.update(model);
            } else {
                mNoteDao.create(model);
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "saveOrUpdate: ", e);
            return false;
        }
    }

    public boolean checkNoteField(NoteModel noteModel) {
        if (noteModel == null) {
            return false;
        }
        if (StringUtils.isBlank(noteModel.getTitle())) {
            ToastUtils.showToastShort("标题不能为空");
            return false;
        }
        if (noteModel.getTime() <= 0) {
            ToastUtils.showToastShort("时间格式错误");
            return false;
        }
        if (StringUtils.isBlank(noteModel.getContent())) {
            ToastUtils.showToastShort("内容不能为空");
            return false;
        }
        return true;
    }
}
