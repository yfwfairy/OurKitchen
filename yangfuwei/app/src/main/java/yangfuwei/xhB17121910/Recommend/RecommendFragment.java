package yangfuwei.xhB17121910.Recommend;

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

import yangfuwei.xhB17121910.Recommend.RecommendListModel;
import yangfuwei.xhB17121910.Recommend.RecommendListViewAdapter;
import yangfuwei.xhB17121910.Recommend.RecommendViewModel;
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
        mListView.setAdapter(new RecommendListViewAdapter(getContext(), articleList));
        return view;
    }

    public void initLocalData() {
        articleList = new ArrayList<>();
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "宫保鸡丁色泽赤红诱人，酸辣口，鸡肉滑嫩，花生米爽脆，大葱也好吃。是一道超级下饭菜，很多饭店都有宫保鸡丁盖浇饭，和宫保鸡丁面。可见它多么随和，怎么打发都可以哈！", "@laoyang", "https://i8.meishichina.com/attachment/recipe/2016/05/31/20160531ffi78om0rjfzx745.JPG?x-oss-process=style/p800", "小黄"));
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "缤纷咕噜鸡球炸至酥脆的鸡胸肉裹上酸甜的糖醋汁，口感不柴不油，酸甜可口又开胃，好吃到让人放不下筷子～", "@微笑向前行", "https://i8.meishichina.com/attachment/recipe/2012/03/16/20120316134606513179518.jpg?x-oss-process=style/p800", "小红"));
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "鲈鱼，肉质鲜嫩，营养价值高，口感很好，容易消化。这道起片鲈鱼去掉了鱼刺，片成鱼片入锅蒸，浇汁儿，造型好看，上桌大气。大片鱼肉，不用担心鱼刺，吃起来简直不要太爽", "@私家厨", "https://i8.meishichina.com/attachment/recipe/2014/06/11/20140611141600916151400.jpg?x-oss-process=style/p800", "小黄"));
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "香酥的脆皮鸡翅，自己在家也能轻松制作。", "@泽瑞妈妈", "https://i8.meishichina.com/attachment/recipe/2016/09/07/20160907e45su9cmsgmguwxk.jpg?x-oss-process=style/p800", "小绿"));
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "红红烧排骨颜色酱红，咸甜适口", "@溪月", "https://i8.meishichina.com/attachment/recipe/201112/13/201112131045396.jpg?x-oss-process=style/p800", "小明"));
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "麻婆豆腐是清同治初年成都一家小饭店店主之妻刘氏所创制，刘氏面部有麻点，她所做的豆腐闻名乡里，“麻婆豆腐”从此成为一道名菜...", "@馨玉梦境", "https://i8.meishichina.com/attachment/recipe/2013/01/04/20130104191936376888924.jpg?x-oss-process=style/p800", "小杨"));
        articleList.add(new RecommendListModel(System.currentTimeMillis(), "很多人都爱喝小米粥，因为都知道小米的营养价值很高，自古以来就是物美价廉的营养补品。", "@悠哈厨房", "https://i8.meishichina.com/attachment/recipe/2016/11/05/2016110514782971811929781866.jpg?x-oss-process=style/p800", "小羊"));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecommendViewModel.class);
        // TODO: Use the ViewModel
    }

}
