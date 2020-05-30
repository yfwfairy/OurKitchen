package yangfuwei.xhB17121910.Note;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.R;
import yangfuwei.xhB17121910.Utils.Constants;

public class MyNoteFragment extends Fragment {

    public final static int REQUEST_CODE = 1;
    public final static int RESULT_OK = 1;
    private FloatingActionButton mFloatingActionButton;
    private ListView myNoteListView;
    private List<NoteModel> myNoteList;
    private NoteListViewAdapter mNoteListViewAdapter;
    public NiceSpinner titleSpinner;
    public TextView totalSizeTxv;

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

        totalSizeTxv = view.findViewById(R.id.totalSizeTxv);
        titleSpinner = view.findViewById(R.id.spinner);
        titleSpinner.setTextSize(30);
        titleSpinner.setBackgroundResource(R.color.splitColor);
        List<String> dataSet = new LinkedList<>(Arrays.asList("全部分类",NoteType.FRIED.getName(),NoteType.PRINCIPLE.getName(),NoteType.STREET.getName()));
        titleSpinner.attachDataSource(dataSet);
        titleSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                refreshMyNotes();
            }
        });

        myNoteListView = view.findViewById(R.id.my_note_listview);
        myNoteListView.setDivider(null);
        initMyNotes();
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
        myNoteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int index = position;
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("提示")
                        .setMessage("删除该记录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NoteModel noteModel = myNoteList.get(index);
                                if (noteModel != null) {
                                    int id = noteModel.getId();
                                    NoteManager.getInstance().removeNote(id);
                                    refreshMyNotes();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                alertDialog.show();
                return true;
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
            }
        }
    }

    public void initMyNotes() {
        myNoteList = NoteManager.getInstance().findAll();
        if (mNoteListViewAdapter == null) {
            mNoteListViewAdapter = new NoteListViewAdapter(getContext(),myNoteList);
        } else {
            mNoteListViewAdapter.setNoteList(myNoteList);
        }
        totalSizeTxv.setText(myNoteList.size()+"");
    }

    public void refreshMyNotes() {
        int type = titleSpinner.getSelectedIndex();
        myNoteList = NoteManager.getInstance().findType(type);
        if (mNoteListViewAdapter == null) {
            mNoteListViewAdapter = new NoteListViewAdapter(getContext(),myNoteList);
        } else {
            mNoteListViewAdapter.setNoteList(myNoteList);
        }
        totalSizeTxv.setText(myNoteList.size()+"");
        myNoteListView.setAdapter(mNoteListViewAdapter);
    }
}
