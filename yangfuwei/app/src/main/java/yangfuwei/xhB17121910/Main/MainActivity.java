package yangfuwei.xhB17121910.Main;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import yangfuwei.xhB17121910.Find.FindFragment;
import yangfuwei.xhB17121910.Note.NoteFragment;
import yangfuwei.xhB17121910.R;
import yangfuwei.xhB17121910.Recommend.RecommendFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationViewEx bottomTabBar;

    MyViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bottomTabBar = findViewById(R.id.bottomTabBar);

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setCanSlide(false);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new RecommendFragment();
                    case 1:
                        return new NoteFragment();
                    case 2:
                        return new FindFragment();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        bottomTabBar.setupWithViewPager(mViewPager);

    }
}
