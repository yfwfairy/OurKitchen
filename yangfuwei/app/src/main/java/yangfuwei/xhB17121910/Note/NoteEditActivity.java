package yangfuwei.xhB17121910.Note;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import jp.wasabeef.richeditor.RichEditor;
import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.R;

public class NoteEditActivity extends AppCompatActivity {

    private static final String TAG = "NoteEditActivity";
    private NoteModel mNoteModel;
    private Button backButton;
    private Button redoButton;
    private Button undoButton;
    private Button saveButton;
    private EditText titleEdt;
    private RichEditor mRichEditor;
    private BottomNavigationViewEx bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        mNoteModel = getIntent().getParcelableExtra("note");
        if (mNoteModel == null) {
            mNoteModel = new NoteModel();
        }
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
                finish();
            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_checklist:
                        mRichEditor.setBullets();
                        break;
                    case R.id.tab_image:
                        mRichEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                                "testImage");
                        break;
                }
                return false;
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mNoteModel.setTime(System.currentTimeMillis());
                mNoteModel.setTitle(titleEdt.getText().toString());
                mNoteModel.setContent(mRichEditor.getHtml());
                mNoteModel.setAuther("Me");
                NoteManager.getInstance().saveOrUpdate(mNoteModel);
                Intent i = new Intent();
                setResult(MyNoteFragment.RESULT_OK, i);
                finish();
            }
        });

        titleEdt.setText(mNoteModel.getTitle());
        mRichEditor.setHtml(mNoteModel.getContent());
    }
}
