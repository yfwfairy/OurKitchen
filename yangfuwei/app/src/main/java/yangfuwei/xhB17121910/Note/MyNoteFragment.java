package yangfuwei.xhB17121910.Note;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.R;

public class MyNoteFragment extends Fragment {


    private final static int REQUEST_CODE = 1;
    private final static int RESULT_OK = 1;
    private FloatingActionButton mFloatingActionButton;
    private ListView myNoteListView;
    private List<NoteModel> myNoteList;

    public MyNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_note, container, false);
        mFloatingActionButton = view.findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NoteEditActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        initLocalData();
        myNoteListView = view.findViewById(R.id.my_note_listview);
        myNoteListView.setAdapter(new NoteListViewAdapter(getContext(), myNoteList));
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                // return new item
                // updatelist from database
            }
        }
    }

    public void initLocalData() {
        myNoteList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            myNoteList.add(new NoteModel(System.currentTimeMillis(), "笔记标题" + i, "作者" + i, null, "笔记内容" + i));
        }
    }
}
