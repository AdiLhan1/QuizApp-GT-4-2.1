package com.geektech.quizapp_gt_4_2.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.history.HistoryFragment;
import com.geektech.quizapp_gt_4_2.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    private ViewPager viewPager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.main_view_pager); //Init Viewpager
        setupFm(getSupportFragmentManager(), viewPager); //Setup Fragment
        viewPager.setCurrentItem(0); //Set Currrent Item When Activity Start
        viewPager.setOnPageChangeListener(new PageChange());
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private static class MainPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> Fragment = new ArrayList<>(); //Fragment List
        private List<String> NamePage = new ArrayList<>();

        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void add(Fragment Frag, String Title) {
            Fragment.add(Frag);
            NamePage.add(Title);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return NamePage.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

    public static void setupFm(FragmentManager fragmentManager, ViewPager viewPager) {
        MainPagerAdapter Adapter = new MainPagerAdapter(fragmentManager);
        //Add All Fragment To List
        Adapter.add(new MainFragment(), "Page One");
        Adapter.add(new HistoryFragment(), "Page Two");
        Adapter.add(new SettingsFragment(), "Page Three");
        viewPager.setAdapter(Adapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_history:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_settings:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    public class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    navigation.setSelectedItemId(R.id.navigation_main);
                    break;
                case 1:
                    navigation.setSelectedItemId(R.id.navigation_history);
                    break;
                case 2:
                    navigation.setSelectedItemId(R.id.navigation_settings);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

}
