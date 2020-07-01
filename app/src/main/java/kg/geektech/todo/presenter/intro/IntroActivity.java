package kg.geektech.todo.presenter.intro;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import kg.geektech.todo.R;
import kg.geektech.todo.data.database.AppPreferences;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseActivity;
import kg.geektech.todo.presenter.main.MainActivity;

public class IntroActivity extends BaseActivity {
    ViewPager viewPager;
    Button skip, nextbtn;
    String start, next ;
    DotsIndicator dotsIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initViews();
        onViewPager();
    }

    public void initViews(){
        skip = findViewById(R.id.skip_button);
        nextbtn = findViewById(R.id.next_button);
        dotsIndicator=findViewById(R.id.dots_indicator);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new IntroPagerAdapter(getSupportFragmentManager()));
        dotsIndicator.setViewPager(viewPager);
    }

    public void showButtons() {
        skip.setVisibility(View.VISIBLE);
        nextbtn.setVisibility(View.VISIBLE);
        nextbtn.setText(next);
    }

    public void setButNameSkipGone() {
        nextbtn.setText(start);
        skip.setVisibility(View.GONE);

    }

    public void skip(View view) {
        new AppPreferences(IntroActivity.this).setLaunched(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
        toast("skip button");
    }

    public void next(View view) {
        if (viewPager.getCurrentItem() <IntroPagerAdapter.NUM_ITEMS ) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    public void onViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == IntroPagerAdapter.NUM_ITEMS) {
                    setButNameSkipGone();
                } else {
                    showButtons();
                }
            }
        });
    }

   public static class IntroPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_ITEMS = 3;

        public IntroPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return IntraFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}

