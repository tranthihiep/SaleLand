package com.example.hiep.bds.view.detailData;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.example.hiep.bds.Presenter.DataDetailPre;
import com.example.hiep.bds.Presenter.GetDataDetail;
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
import com.example.hiep.bds.adapter.GridviewAdapter;
import com.example.hiep.bds.adapter.ViewPagerAdapter;
import com.example.hiep.bds.model.Convenience;
import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.model.Distance;
import com.example.hiep.bds.model.User;
import com.example.hiep.bds.utilts.ExpandableTextView;
import com.example.hiep.bds.utilts.GetFragment;
import com.example.hiep.bds.utilts.Unit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class DetailData extends AppCompatActivity
        implements View.OnClickListener , GetDataDetailView {
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private GetDataDetail iGetDiscoverPre;
    private static ArrayList<String> mConveniences = new ArrayList<>();
    private static List<Distance> mdistance = new ArrayList<>();
    private static User mUser = new User();
    private ImageView img1,img2, imgFull;
    private String[] mTitles = { "Chi tiết", "Liên hệ","Bản đồ" };
    private TextView txtTitle, txtPrice, txtLoaction, txtApartment,
            txtS, txtBedRoom, txtBath,txtBats,txtFlood,txtSale,txtbelcony,
            txtDola,txtThoathua;
    private AgentInfor mAgentInforTab = new AgentInfor();
    private f mDescriptionTab = new f();
    private MapsTab mMapsTab = new MapsTab();
    private String  mNameMovies;
    private int id;
    private static String summary,title;
    private static float longi = 0;
    private static  float lat = 0;

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
        getDataMovieFromService();
        setView();
    }
    private void setView() {
        setTabLayout();
    }

    private void getDataMovieFromService() {

        iGetDiscoverPre.getDetailMovie(id);
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
        txtbelcony = findViewById(R.id.belcony);
        txtFlood = findViewById(R.id.flod);
        txtDola = findViewById(R.id.giadola);
        mViewPager = (ViewPager) findViewById(R.id.viewPagerDetails);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        iGetDiscoverPre = new DataDetailPre(this);
        txtThoathua = findViewById(R.id.thoathuan);
    }

    private void setTabLayout() {
        mFragments = new ArrayList<>();
        mFragments.add(mDescriptionTab);
        mFragments.add(mAgentInforTab);
        mFragments.add(mMapsTab);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void getBundle() {
        Bundle b = getIntent().getExtras();
        id  = b.getInt("id");
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void getDetailMovieSuccess(DataDetail movieDetails) {
        mNameMovies = movieDetails.getTitle();
        mdistance = movieDetails.getDistances();
        mUser = movieDetails.getUser();
        longi = Float.parseFloat(movieDetails.getLongitude());
        longi = Float.parseFloat(movieDetails.getLongitude());
        lat = Float.parseFloat(movieDetails.getLatitude());
        title = movieDetails.getTitle();
        for (Convenience movie : movieDetails.getConveniences()) {
                mConveniences.add(movie.getName());
            }
        summary = movieDetails.getDescription();
        Picasso.get().load("http://project-property.herokuapp.com/uploads/images/"+ movieDetails.getImage()).into(imgFull);
        txtFlood.setText(movieDetails.getDetail().getFloor()+" Tầng");
        if (movieDetails.getPurpose().toString().equalsIgnoreCase("sale")){
            txtSale.setText("Mục đích: Bán");
        }else txtSale.setText("Mục đích: Cho thuê");
        if (movieDetails.getNegotiable() == 1){
            txtThoathua.setText("Thỏa thuận");
        }else  txtThoathua.setText("Không thỏa thuận");

        txtS.setText(movieDetails.getArea()+ " m²");
        txtLoaction.setText(movieDetails.getAddress());
        txtBedRoom.setText(movieDetails.getDetail().getBedRoom()+" Phòng ngủ");
        txtBath.setText(movieDetails.getDetail().getBath()+ " Phòng tắm");
        txtBats.setText(movieDetails.getDetail().getToilet()+ " Toilet");
        txtPrice.setText(Unit.formatPrice(Long.valueOf(movieDetails.getPrice()))+" VND/ " + movieDetails.getUnit());
        txtDola.setText(Unit.formatPrice(Long.valueOf(movieDetails.getPrice()))+" VND/ " + movieDetails.getUnit());
        txtApartment.setText(movieDetails.getPropertyType().getName());
        txtbelcony.setText(movieDetails.getDetail().getBalcony() +" Ban công");
        txtTitle.setText(movieDetails.getTitle());
    }

    @Override
    public void getDetailMovieFailure() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    public static class f extends GetFragment {

        public ExpandableTextView mExpandableTextView;
        GridView mGridView;
        RecyclerView mGridViewDistance;
        private GridLayoutManager layoutManager;
        public f() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.tab_description, container, false);
            mExpandableTextView = view.findViewById(R.id.description);
            mGridViewDistance = view.findViewById(R.id.gridViewDistance);
            mExpandableTextView.setText(summary);
            mGridView = view.findViewById(R.id.gridView);
            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1 ,mConveniences );
            mGridView.setAdapter(arrayAdapter);
            layoutManager = new GridLayoutManager(getActivity(), 2);
            mGridViewDistance.setHasFixedSize(true);
            mGridViewDistance.setLayoutManager(layoutManager);
            GridviewAdapter adapter = new GridviewAdapter(mdistance,R.layout.item_distance,getActivity());
            mGridViewDistance.setAdapter(adapter);
            return view;
        }
    }

    public static class AgentInfor extends GetFragment {
        ImageView mImageViewAvatar;
        TextView mTextViewTen,mTextViewDC,mTextViewClick;

        public AgentInfor() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.tab_agent_infor, container, false);
            mImageViewAvatar = view.findViewById(R.id.avatar);
            mTextViewTen = view.findViewById(R.id.name_avatar);
            mTextViewDC = view.findViewById(R.id.addressAvatar);
            mTextViewClick = view.findViewById(R.id.txtClickSDT);
            mImageViewAvatar.setImageResource(R.drawable.ic_user);
            mTextViewTen.setText(mUser.getName());
            mTextViewDC.setText(mUser.getAddress());
            mTextViewClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mTextViewClick.getText().toString().equalsIgnoreCase("Click để hiển thị số điện thoại"))
                    {
                        mTextViewClick.setText(mUser.getPhone());
                    }else {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mUser.getPhone(),
                                null));
                        startActivity(intent);
                    }
                }

            });
            return view;
        }
    }
    public static class MapsTab extends GetFragment implements OnMapReadyCallback {

        private GoogleMap mMap;

        public static MapsTab newInstance() {
            MapsTab fragment = new MapsTab();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_maps, null, false);

            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                    .findFragmentById(R.id.fragment_map);
            mapFragment.getMapAsync(this);

            return view;
        }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(lat, longi);
            mMap.addMarker(new MarkerOptions().position(sydney).title(title));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}
