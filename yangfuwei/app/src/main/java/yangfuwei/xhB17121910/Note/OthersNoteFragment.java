package yangfuwei.xhB17121910.Note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import yangfuwei.xhB17121910.R;


public class OthersNoteFragment extends Fragment {

    private OthersNoteViewModel mViewModel;
    private ListView mListView;
    private List<OthersNoteListModel> articleList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_others_note, container, false);
        mListView = view.findViewById(R.id.rec_listview);
        initLocalData();
        mListView.setAdapter(new OthersNoteListViewAdapter(getContext(), articleList));
        return view;
    }

    public void initLocalData() {
        articleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "文章标题" + i, "作者" + i, null, "分享人" + i));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OthersNoteViewModel.class);
        // TODO: Use the ViewModel
    }

}
