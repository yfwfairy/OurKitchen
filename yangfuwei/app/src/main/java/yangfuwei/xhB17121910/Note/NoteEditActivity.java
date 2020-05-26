package yangfuwei.xhB17121910.Note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import jp.wasabeef.richeditor.RichEditor;
import yangfuwei.xhB17121910.R;

public class NoteEditActivity extends AppCompatActivity {

    private static final String TAG = "NoteEditActivity";
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

        mRichEditor.setPlaceholder("请输入");
        mRichEditor.setEditorFontSize(18);
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
                Log.d(TAG, "onNavigationItemSelected: " + menuItem.getMenuInfo());
                return false;
            }
        });



    }
}
