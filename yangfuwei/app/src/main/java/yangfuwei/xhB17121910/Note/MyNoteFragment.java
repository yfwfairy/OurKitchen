package yangfuwei.xhB17121910.Note;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.R;

public class MyNoteFragment extends Fragment {


    public final static int REQUEST_CODE = 1;
    public final static int RESULT_OK = 1;
    private FloatingActionButton mFloatingActionButton;
    private ListView myNoteListView;
    private List<NoteModel> myNoteList;
    private NoteListViewAdapter mNoteListViewAdapter;

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
                intent.putExtra("note",new NoteModel());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        myNoteListView = view.findViewById(R.id.my_note_listview);
        refreshMyNotes();
        myNoteListView.setAdapter(new NoteListViewAdapter(getContext(), myNoteList));
        myNoteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteModel noteModel = myNoteList.get(i);
                if (noteModel != null) {
                    Intent intent = new Intent(getContext(),NoteEditActivity.class);
                    intent.putExtra("note",noteModel);
                    startActivityForResult(intent,REQUEST_CODE);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                refreshMyNotes();
                myNoteListView.setAdapter(mNoteListViewAdapter);
            }
        }
    }

    public void refreshMyNotes() {
        myNoteList = NoteManager.getInstance().findAll();
        if (mNoteListViewAdapter == null) {
            mNoteListViewAdapter = new NoteListViewAdapter(getContext(),myNoteList);
        } else {
            mNoteListViewAdapter.setNoteList(myNoteList);
        }
    }
}
