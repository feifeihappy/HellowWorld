package cn.itcast.huayu.menu.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

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
import cn.itcast.huayu.menu.service.MusicService;
import cn.itcast.huayu.menu.util.LogUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 主activity
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements BDLocationListener {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public String curFragmentTag = "";
    @ViewById(R.id.viewpager)
    ViewPager mViewPager;
    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    private MusicService mMusicService;
    final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("TAG", "onServiceConnected:");
            mMusicService = (( MusicService.MusicServiceBinder)service).getMusicService();
            Log.e("TAG",mMusicService.mText );

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("TAG", "onServiceDisconnected:");
            mMusicService = null;
        }
    };


    private StringBuffer mLocation;
    private long exitTime = 0;

    /**
     * back键
     * Android的程序无需刻意的去退出,当你一按下手机的back键的时候，
     * 系统会默认调用程序栈中最上层Activity的Destroy()方法来， 销毁当前Activity。当此Activity又被其它Activity启动起来的时候,
     * 会重新调用OnCreate()方法进行创建,当栈中所有 Activity都弹出结束后,应用也就随之结束了.
     * 如果说程序中存在service之类的,则可以在恰当的位置监听处理下也就可以了.
     */
    private Intent mIntetnt;

    /**
     * home键
     * Android程序的隐藏,当你按下手机的Home键的时候,系统会默认调用程序栈中最上层Activity的stop()方法,
     * 然后整个应用程序都会被 隐藏起来,当你再次点击手机桌面上应用程序图标时,
     * 系统会调用最上层Activity的OnResume()方法,此时不会重新打开程序,而是直接进入, 会直接显示程序栈中最上层的Activity。
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.getInstance().error("onCreate");

        mIntetnt = new Intent(MainActivity.this, MusicService.class);
        bindService(mIntetnt, mServiceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.getInstance().error("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.getInstance().error("onResume");


    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.getInstance().error("onStop");


    }

    @Override
    protected void onDestroy() {
        stopService(mIntetnt);
        LogUtil.getInstance().error("MainActivity_____onDestroy");
        super.onDestroy();

    }


    @AfterViews
    void initView() {


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

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000;
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
//        LogUtil.getInstance().error(sb.toString());
        mLocation = sb;

        GlobalCache.newInstance().setmLocation(sb);
    }

    /**
     * //不执行父类onkeydown中的方法
     * return true ;
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("TAG", "onKeyDown: ");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 监控返回键
        /*    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("你确定要退出么？")
//                    .setContentText("Won't be able to recover this file!")
                    .setCancelText("取消")
                    .setConfirmText("确认")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.cancel();
                            MainActivity.this.finish();
                        }
                    })
                    .show();*/
            ExitApp();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            // 监控菜单键
            Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("TAG", "onBackPressed: ");

    }

    public void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            MainActivity.this.finish();
        }
    }


}

