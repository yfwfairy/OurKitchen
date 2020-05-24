package yangfuwei.xhB17121910;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    AnimatedBottomBar bottomTabBar;

    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabBar = findViewById(R.id.bottomTabBar);
        mViewPager = findViewById(R.id.viewPager);
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
