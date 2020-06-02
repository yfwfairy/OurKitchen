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
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "黄金鸡翅", "小杨" , "https://i8.meishichina.com/attachment/recipe/2016/09/01/20160901147269820369413.jpg?x-oss-process=style/p800"));
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "鱼香肉丝", "小孙" , "https://i8.meishichina.com/attachment/recipe/2015/05/04/201505040509d356adb584ab.jpg?x-oss-process=style/p800"));
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "番茄炒蛋", "小孙" , "https://i8.meishichina.com/attachment/recipe/201103/201103011423117.jpg?x-oss-process=style/p800"));
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "土豆炖牛肉", "小杨" , "https://i8.meishichina.com/attachment/recipe/201102/201102181345266.jpg?x-oss-process=style/p800"));
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "香菇油麦菜", "小孙" , "https://i8.meishichina.com/attachment/recipe/2018/07/17/20180717153182590998810885274.jpg?x-oss-process=style/p800"));
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "可乐鸡翅", "小孙" , "https://i8.meishichina.com/attachment/recipe/2014/07/22/20140722221138274796659.jpg?x-oss-process=style/p800"));
        articleList.add(new OthersNoteListModel(System.currentTimeMillis(), "蒜蓉金针菇", "小杨" , "https://i8.meishichina.com/attachment/recipe/2015/06/12/2015061244050109f7be07d8.jpg?x-oss-process=style/p800"));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OthersNoteViewModel.class);
        // TODO: Use the ViewModel
    }

}
