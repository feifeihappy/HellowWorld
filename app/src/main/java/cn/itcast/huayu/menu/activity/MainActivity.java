package cn.itcast.huayu.menu.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.adapter.MyViewPagerAdapter;
import cn.itcast.huayu.menu.cache.GlobalCache;
import cn.itcast.huayu.menu.common.MyLocationListener;
import cn.itcast.huayu.menu.fragment.FragmentOne;
import cn.itcast.huayu.menu.fragment.FragmentThree;
import cn.itcast.huayu.menu.fragment.FragmentTwo;
import cn.itcast.huayu.menu.util.LogUtil;

/**
 * 主activity
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements BDLocationListener {

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;
    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private StringBuffer mLocation;
    public String curFragmentTag = "";
    @AfterViews
    void initView() {
        //开启一个服务
//        Intent mIntent = new Intent();
//        mIntent.setClass(this, LocationService.class);
//        startService(mIntent);
        mLocationClient = new LocationClient(getApplicationContext());     //第一步，初始化LocationClient类
//        mLocationClient.registerLocationListener( myListener );    //注册监听函数
        mLocationClient.registerLocationListener(MainActivity.this);
        initLocation();//第二步，配置定位SDK参数
        mLocationClient.start();

        MyViewPagerAdapter mFragmentAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mFragmentAdapter.addfragment(FragmentOne.newInstance(), "菜谱");
        mFragmentAdapter.addfragment(FragmentTwo.newInstance(), "天气");
        mFragmentAdapter.addfragment(FragmentThree.newInstance(), "手机号");
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.addTab(mTabLayout.newTab().setText("第一个fragment"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("第二个fragment"));
        mTabLayout.addTab(mTabLayout.newTab().setText("第三个fragment"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联Vi
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
//        longitude and latitude
        StringBuffer sb = new StringBuffer(256);
        sb.append(bdLocation.getLatitude());
        sb.append(bdLocation.getLongitude());
        sb.append(bdLocation.getCity());
        LogUtil.getInstance().error(sb.toString());
        mLocation = sb;

        GlobalCache.newInstance().setmLocation(sb);
    }
}

