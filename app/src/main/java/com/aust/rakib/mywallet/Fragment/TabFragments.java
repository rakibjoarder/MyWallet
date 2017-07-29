package com.aust.rakib.mywallet.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aust.rakib.mywallet.Adapter.ViewPagerAdapter;
import com.aust.rakib.mywallet.Fragment.UsageHistoryFragment;
import com.aust.rakib.mywallet.Fragment.UseWalletFragment;
import com.aust.rakib.mywallet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragments extends Fragment {
TabLayout tabLayout;
    ViewPager viewPager;

    public TabFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usage_history_fragments, container, false);

        tabLayout= (TabLayout) view.findViewById(R.id.tablayout);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter =new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new UsageHistoryFragment(),"Usage History");
        adapter.addFragment(new SavedHistoryFragment(),"Saved Records");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return view;
    }

}
