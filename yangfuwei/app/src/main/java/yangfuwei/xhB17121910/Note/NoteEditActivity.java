package yangfuwei.xhB17121910.Note;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;
import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.R;
import yangfuwei.xhB17121910.Utils.BitmapUtils;
import yangfuwei.xhB17121910.Utils.Constants;
import yangfuwei.xhB17121910.Utils.DataTimeUtils;
import yangfuwei.xhB17121910.Utils.ParcelableUtils;
import yangfuwei.xhB17121910.Utils.QiniuUtils;

public class NoteEditActivity extends AppCompatActivity {

    private static final String TAG = "NoteEditActivity";
    private NoteModel mNoteModel;
    private NoteModel fistCreateNote;
    private Button backButton;
    private Button redoButton;
    private Button undoButton;
    private Button saveButton;
    private NiceSpinner typeSpinner;
    private TextView timeTxv;
    private EditText titleEdt;
    private RichEditor mRichEditor;
    private BottomNavigationViewEx bottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        mNoteModel = getIntent().getParcelableExtra("note");
        if (mNoteModel == null) {
            mNoteModel = new NoteModel();
        }
        fistCreateNote = ParcelableUtils.copy(mNoteModel);
        initUI();

    }


    public void initUI() {
        backButton = findViewById(R.id.back);
        redoButton = findViewById(R.id.redo);
        undoButton = findViewById(R.id.undo);
        saveButton = findViewById(R.id.save);
        titleEdt = findViewById(R.id.title_edittext);
        mRichEditor = findViewById(R.id.richeditor);
        bottomNavigation = findViewById(R.id.bottom_nav);
        typeSpinner = findViewById(R.id.type_spinner);
        timeTxv = findViewById(R.id.timeTxv);

        timeTxv.setText(DataTimeUtils.stampToDate(String.valueOf(mNoteModel.getTime())));
        timeTxv.setTextSize(14);
        typeSpinner.setTextSize(14);
        List<String> dataSet = new LinkedList<>(Arrays.asList(NoteType.DEFAULT.getName(),NoteType.FRIED.getName(),NoteType.PRINCIPLE.getName(),NoteType.STREET.getName()));
        typeSpinner.attachDataSource(dataSet);
        typeSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                mNoteModel.setType(position);
            }
        });
        typeSpinner.setSelectedIndex(mNoteModel.getType());
        mRichEditor.setEditorFontSize(22);
        mRichEditor.setEditorHeight(500);
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRichEditor.redo();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRichEditor.undo();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNoteModel.setTitle(titleEdt.getText().toString());
                mNoteModel.setContent(mRichEditor.getHtml());
                if (fistCreateNote.equals(mNoteModel)) {
                    finish();
                    return;
                }
                Log.d(TAG, "yfw onClick: equals first:" + fistCreateNote.toString() + " current:" + mNoteModel.toString());
                AlertDialog alertDialog = new AlertDialog.Builder(NoteEditActivity.this)
                        .setTitle("退出程序")
                        .setMessage("你还没有保存，是否退出编辑")
                        .setPositiveButton("退出并保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                updateDataBase();
                                finish();
                            }
                        })
                        .setNegativeButton("放弃编辑", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).create();
                alertDialog.show();
            }
        });


 //       bottomNavigation.setSelectedItemId(bottomNavigation.getMenu().getItem(position).getItemId());
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_checklist:
                        mRichEditor.setBullets();
                        break;
                    case R.id.tab_image:
                        selectPicture();
                        break;
                }
                return false;
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateDataBase();
                finish();
            }
        });

        titleEdt.setText(mNoteModel.getTitle());
        mRichEditor.setHtml(mNoteModel.getContent());

    }

    public void updateDataBase() {
        mNoteModel.setTitle(titleEdt.getText().toString());
        mNoteModel.setContent(mRichEditor.getHtml());
        mNoteModel.setAuther("Me");
        NoteManager.getInstance().saveOrUpdate(mNoteModel);
        Intent i = new Intent();
        setResult(MyNoteFragment.RESULT_OK, i);
    }

    public void selectPicture() {
        PictureSelector.create(NoteEditActivity.this,PictureSelector.SELECT_REQUEST_CODE).
                selectPicture(false,1000,1000,1,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean == null) return;
                String token = QiniuUtils.getToken();
                Log.d(TAG, "token:" + token);
                Configuration configuration = QiniuUtils.getDefaultConfigurations();
                UploadManager uploadManager = new UploadManager(configuration);
                if (pictureBean.isCut()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(pictureBean.getPath());
                    if (bitmap == null) {
                        Log.d(TAG, "onActivityResult: bitmap null");
                        return;
                    }
                    File file = BitmapUtils.compressImage(bitmap);
                    uploadManager.put(file, null, token, new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject response) {
                            if (response == null) return;
                            String url = "";
                            try {
                                url = response.getString("key");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "complete: url:" + url);
                            url = QiniuUtils.DOMAIN + url;
                            Log.d(TAG, "complete: url:" + url);
                            mRichEditor.insertImage(url,url);
                            if (TextUtils.isEmpty(mNoteModel.getImageUrl())) {
                                mNoteModel.setImageUrl(url);
                            }
                        }
                    },null);
                } else {
                    Bitmap bitmap = BitmapUtils.getimage(pictureBean.getPath());
                    File file = BitmapUtils.compressImage(bitmap);
                    uploadManager.put(file, null, token, new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject response) {
                            if (response == null) return;
                            String url = "";
                            try {
                                url = response.getString("key");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "complete: url:" + url);
                            url = QiniuUtils.DOMAIN + url;
                            Log.d(TAG, "complete: url:" + url);
                            mRichEditor.insertImage(url,url);
                            if (TextUtils.isEmpty(mNoteModel.getImageUrl())) {
                                mNoteModel.setImageUrl(url);
                            }
                        }
                    },null);
                }
            }
        }
    }
}
