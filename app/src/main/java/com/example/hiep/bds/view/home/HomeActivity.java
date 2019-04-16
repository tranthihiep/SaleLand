package com.example.hiep.bds.view.home;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.ViewPagerAdapter;
import com.example.hiep.bds.utilts.MainContainFragment;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    public static ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private String[] mTitles = { "Mua", "Thuê"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView()
//                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
        setContentView(R.layout.activity_home);
        init();
        setDrawerLayout();
        setClickNavigationView();
        mFragments = new ArrayList<>();

        MainContainFragment fragment = new MainContainFragment();
        fragment.setRoot("Mua");
        mFragments.add(fragment);

        fragment = new MainContainFragment();
        fragment.setRoot("Thuê");
        mFragments.add(fragment);
        addControls();
    }

    private void setDrawerLayout() {
        mToggle = new ActionBarDrawerToggle(HomeActivity.this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.setDrawerIndicatorEnabled(true);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setClickNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuHome:
                                Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menuDangKy:
                                break;
                            case R.id.menuDangNhap:
                                break;
                            case R.id.menuLienHe:
                                break;
                        }

                        return true;
                    }
                });
    }
    private void init(){
        mViewPager = findViewById(R.id.viewPagerMain);
        mTabLayout = findViewById(R.id.tabLayoutMain);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutaa);
        mNavigationView = findViewById(R.id.navigationView);
    }
    private void addControls() {

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.setupWithViewPager(mViewPager);
        setIcon();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Fragment fragment = mViewPagerAdapter.getItem(mViewPager.getCurrentItem());
                if (fragment instanceof MainContainFragment) {
                    ((MainContainFragment) fragment).isFragment();
                }
            }
        });
    }

    private void setIcon() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_sale);
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_rent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        boolean isAllowBack = true;
        Fragment fragment = mViewPagerAdapter.getItem(mViewPager.getCurrentItem());
        if (fragment instanceof MainContainFragment) {
            isAllowBack = ((MainContainFragment) fragment).callBack();
        }

        if (isAllowBack) {
            if (mViewPager.getCurrentItem() != 0) {
                mViewPager.setCurrentItem(0);
            } else {
                super.onBackPressed();
            }
        }
    }
}
