package kg.geektech.todo.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseActivity;
import kg.geektech.todo.model.BoredAction;
import kg.geektech.todo.presenter.favorites.FavoritesFragment;
import kg.geektech.todo.presenter.home.HomeFragment;
import kg.geektech.todo.presenter.intro.IntroActivity;


public class MainActivity extends BaseActivity {
    ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;
    private ArrayList<BoredAction> list = new ArrayList<>();


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager_bottom);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        createNavigationBottomWithViewPager();
        isFirstLaunch();

    }
    private void isFirstLaunch() {
        if (App.appPreferences.isFirstLaunched()) {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
        }
    }


    private void createNavigationBottomWithViewPager() {
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.page_2:
                        mViewPager.setCurrentItem(1, false);
                        break;

                }
                return true;
            }
        });
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = HomeFragment.newInstance();
                    break;

                default:
                    fragment = FavoritesFragment.newInstance();
            };
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}







