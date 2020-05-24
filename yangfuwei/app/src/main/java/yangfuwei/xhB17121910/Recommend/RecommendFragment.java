package yangfuwei.xhB17121910.Recommend;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yangfuwei.xhB17121910.R;

public class RecommendFragment extends Fragment {

    private RecommendViewModel mViewModel;
    private ListView mListView;
    private List<RecommendListModel> articleList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_fragment, container, false);
        mListView = view.findViewById(R.id.rec_listview);
        initLocalData();
        mListView.setAdapter(new RecommendListViewAdapter(getContext(),articleList));
        return view;
    }

    public void initLocalData() {
        articleList = new ArrayList<>();
        for (int i = 0 ; i < 20; i++) {
            articleList.add(new RecommendListModel(System.currentTimeMillis(),"文章标题" + i , "作者" + i , null,"分享人" + i));
        }
}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecommendViewModel.class);
        // TODO: Use the ViewModel
    }

}
