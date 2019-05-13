package com.example.hiep.bds.view.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.ViewPagerAdapter;
import com.example.hiep.bds.model.User;
import com.example.hiep.bds.model.modelLocation.Phuong;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.CustemSpinner;
import com.example.hiep.bds.utilts.MainContainFragment;
import com.example.hiep.bds.view.myPost.MyPostActivity;
import com.example.hiep.bds.view.postAD.LocationInforActivity;
import com.example.hiep.bds.view.postAD.PorposeActivity;
import com.example.hiep.bds.view.user.LoginActivity;
import com.example.hiep.bds.view.user.RegisterActivity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    public static String  TOKEN= null;
    public static ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private String[] mTitles = { "Mua, Bán", "Cho Thuê"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getSupportActionBar().setTitle("");


        SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);
        TOKEN = sp1.getString("token", null);
        if (TOKEN!=null){

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<User> call = apiService.me("Bearer "+ TOKEN);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 200) {
                        getSupportActionBar().setTitle("Chào bạn "+response.body().getName());
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        }

        setContentView(R.layout.activity_home);
        init();
        setDrawerLayout();
        setClickNavigationView();
        mFragments = new ArrayList<>();

        MainContainFragment fragment = new MainContainFragment();
        fragment.setRoot("Mua, Bán");
        mFragments.add(fragment);

        fragment = new MainContainFragment();
        fragment.setRoot("Cho Thuê");
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
                            case R.id.menuDangKy:
                                Intent intesnt = new Intent(HomeActivity.this,RegisterActivity.class);
                                startActivity(intesnt);
                                break;
                            case R.id.menuDangNhap:
                                Intent intent1 = new Intent(HomeActivity.this,LoginActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.menuDangXuat:
                                SharedPreferences preferences =getSharedPreferences("Login",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.commit();
                                Intent intent3 = new Intent(HomeActivity.this,HomeActivity.class);
                                startActivity(intent3);
                                break;
                            case R.id.menuMyPost:
                                if (HomeActivity.TOKEN != null) {
                                    Intent intent2 = new Intent(getApplicationContext(), MyPostActivity.class);
                                    startActivity(intent2);
                                }else {
                                    Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent2);

                                }
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
