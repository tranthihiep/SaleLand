package com.example.hiep.bds.view.detailData;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.example.hiep.bds.R;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hiep.bds.adapter.ViewPagerAdapter;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.model.User;
import com.example.hiep.bds.utilts.ExpandableTextView;
import com.example.hiep.bds.utilts.GetFragment;
import com.example.hiep.bds.utilts.Unit;
import com.example.hiep.bds.view.tab.AgentInforTab;
import com.example.hiep.bds.view.tab.MapsTab;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class DetailData extends AppCompatActivity
        implements View.OnClickListener {
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<User> mListCast;
   // private List<Map> mMoreMovies;
    private ImageView img1,img2, imgFull;
    private String[] mTitles = { "Mô tả", "Liên hệ" };
    private TextView txtTitle, txtPrice, txtLoaction, txtApartment,
            txtS, txtBedRoom, txtBath,txtBats,txtFlood,txtSale,txtbelcony,txtDinning,
            txtLiving;
    private AgentInforTab mAgentInforTab = new AgentInforTab();
    private f mDescriptionTab = new f();
    private MapsTab mMapsTab = new MapsTab();
    private String  mNameMovies;
    private static String summary;
    private Datum mDatum = new Datum();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_detail_ads);
        init();
        setToolBar();
        getBundle();
        haha();
        setView();
    }
    private void setView() {
        setTabLayout();
    }


    private void setToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mToolbar.setTitle("");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
        ((AppBarLayout)findViewById(R.id.appbarmovie)).addOnOffsetChangedListener(
                new AppBarLayout.OnOffsetChangedListener() {
                    boolean isShow = false;
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (verticalOffset != 0)
                        {             mCollapsingToolbarLayout.setTitleEnabled(false);
                            mToolbar.setTitle(mNameMovies);
                            isShow = true;
                        }
                        else
                        {
                            mCollapsingToolbarLayout.setTitleEnabled(false);
                            mToolbar.setTitle("");
                            isShow = false;
                        }
                    }
                });
    }
    private void init() {
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_movie);
        mToolbar = findViewById(R.id.toolBarMovieDetail);
        imgFull = findViewById(R.id.img_full);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        txtTitle =findViewById(R.id.txtTittle);
        txtApartment = findViewById(R.id.apartment);
        txtPrice = findViewById(R.id.txtPrice);
        txtBath = findViewById(R.id.txtBath);
        txtBats = findViewById(R.id.baths);
        txtBedRoom = findViewById(R.id.txtBedRomm);
        txtLoaction = findViewById(R.id.txtLoaction);
        txtS = findViewById(R.id.S);
        txtSale = findViewById(R.id.sale);
        txtDinning =findViewById(R.id.txtDinning);
        txtbelcony = findViewById(R.id.belcony);
        txtFlood = findViewById(R.id.flod);
        txtLiving = findViewById(R.id.txtLiving);
        mViewPager = (ViewPager) findViewById(R.id.viewPagerDetails);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    private void setTabLayout() {
        mFragments = new ArrayList<>();
        mFragments.add(mDescriptionTab);
        //mFragments.add(mMapsTab);
        mFragments.add(mAgentInforTab);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void getBundle() {
        Bundle b = getIntent().getExtras();
        mDatum  = (Datum) b. getParcelable("id");
    }
        //mNameMovies = movieDetails.getTitle();
        //Picasso.get().load("http://project-property.herokuapp.com/uploads/images/"+movieDetails.getImage()).into(imgFull);

//        txtFlood.setText(movieDetails.getDetail().getFloor());
//        mTextViewDate.setText(utils.convertDate(movieDetails.getTime()));
//        mTextViewTime.setText(movieDetails.getDuration());
//        mTextViewLanguage.setText("Language:  " + movieDetails.getLanguage());
//        mExpand ableTextView.setText(movieDetails.getSummary());
//        mTextViewCountry.setText("(" + movieDetails.getCountry() + ")");
//        utils.randomRating(mRating);
//        mTextViewRating.setText(mRating.getRating() + "/10");
//        mListGenres = movieDetails.getGenres();
//        mTextViewGenres.setText(utils.getNameGenres(mListGenres));
//        mAgentInforTab.setChildFragment(iGetChildFragment);
//        mDescriptionTab.setChildFragment(iGetChildFragment);
//        mListCast = movieDetails.getCasts();
//        mAgentInforTab.getCast(mListCast);
//        mMoreMovies = movieDetails.getMoreMovies();
//        mDescriptionTab.getDataMoreMovie(mMoreMovies);
   // }
private void haha(){
        mNameMovies = mDatum.getTitle();
        summary = mDatum.getDescription();
        Picasso.get().load("http://project-property.herokuapp.com/uploads/images/"+mDatum.getImage()).into(imgFull);
        txtFlood.setText(mDatum.getDetail().getFloor()+" Tầng");
        if (mDatum.getDetail().getLivingRoom()){
            txtLiving.setText("Phòng sinh hoạt");
        }
        if (mDatum.getDetail().getDinningRoom()){
            txtLiving.setText("Phòng ăn");
        }
        txtSale.setText(mDatum.getPurpose());
        txtS.setText(mDatum.getArea()+ " m²");
        txtLoaction.setText(mDatum.getAddress());
        txtBedRoom.setText(mDatum.getDetail().getBedRoom()+" Phòng");
        txtBath.setText(mDatum.getDetail().getBath()+ " Phòng tắm");
        txtBats.setText(mDatum.getDetail().getToilet()+ " Toilet");
        txtPrice.setText(Unit.formatPrice(mDatum.getPrice())+" VND");
        txtApartment.setText(mDatum.getPropertyType().getName());
        txtbelcony.setText(mDatum.getDetail().getBalcony() +" Ban công");
        txtTitle.setText(mDatum.getTitle());
}
    @Override
    public void onClick(View view) {

    }
    public static class f extends GetFragment {

        public ExpandableTextView mExpandableTextView;
        public f() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.tab_description, container, false);
            mExpandableTextView = view.findViewById(R.id.description);
            mExpandableTextView.setText(summary);
            return view;
        }
    }
}
