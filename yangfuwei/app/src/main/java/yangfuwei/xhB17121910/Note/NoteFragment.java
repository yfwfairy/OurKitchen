package yangfuwei.xhB17121910.Note;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.joery.animatedbottombar.AnimatedBottomBar;
import yangfuwei.xhB17121910.Note.Model.NoteViewModel;
import yangfuwei.xhB17121910.R;

public class NoteFragment extends Fragment {

    private NoteViewModel mViewModel;

    private AnimatedBottomBar noteTabbar;
    private ViewPager noteViewPager;

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.note_fragment, container, false);
        noteTabbar = view.findViewById(R.id.tabbar_note);
        noteViewPager = view.findViewById(R.id.note_viewpager);
        noteTabbar.setupWithViewPager(noteViewPager);
        noteViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new MyNoteFragment();
                } else {
                    return new OthersNoteFragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        // TODO: Use the ViewModel
    }

}
