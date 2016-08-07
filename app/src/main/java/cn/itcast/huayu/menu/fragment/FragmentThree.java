package cn.itcast.huayu.menu.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import cn.itcast.huayu.menu.MyApplication;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.activity.MainActivity;
import cn.itcast.huayu.menu.cache.GlobalCache;
import cn.itcast.huayu.menu.model.menu.MenuListData;
import cn.itcast.huayu.menu.util.ToastUtil;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @author ln：zpf on 2016/7/29
 */
public class FragmentThree extends BaseFragment implements BDLocationListener{

    boolean isFirstLoc = true; // 是否首次定位
    public static FragmentThree instance = null;
    public final int msgkey = 1;
    public final int msgToast = 2;
    private TextView mTvTime;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgkey:
                    long systemTime = System.currentTimeMillis();
                    CharSequence mData = DateFormat.format("hh:mm:ss", systemTime);
                    mTvTime.setText(mData);
                    break;
                case msgToast:
                    ToastUtil.showToast(getActivity(), "开了子线程");
                    break;
            }

        }
    };
    private Button mButtonWatch;
    private Button mBtLight;
    private Camera mCamera;
    private boolean state = false;
    private Camera.Parameters parameters;
    private boolean isWatchState = false;
    private Thread mmmWatchThreads;
    private TextView mTvLocation;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;

    public FragmentThree() {
    }

    public static FragmentThree newInstance() {
        if (instance == null) {
            instance = new FragmentThree();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        mButtonWatch = (Button) view.findViewById(R.id.bt_button);
        mBtLight = (Button) view.findViewById(R.id.bt_light);
        mTvLocation = (TextView) view.findViewById(R.id.tv_location);
        //获取地图控件引用
        StringBuffer mLocation = GlobalCache.newInstance().getmLocation();
        //获取定位位置
        mTvLocation.setText(mLocation);
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(FragmentThree.this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);//请求定位间隔
        mLocClient.setLocOption(option);
        mLocClient.start();

        mCamera = Camera.open();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

        mButtonWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isWatchState) {
                    isWatchState = true;
                    mButtonWatch.setText("关闭电子表");
                    if (mmmWatchThreads == null) {
                        (mmmWatchThreads = new Thread(new mWatchThread())).start();
                    }
                } else {
                    mmmWatchThreads.interrupt();
                    mmmWatchThreads = null;
                    isWatchState = false;
                    mButtonWatch.setText("打开电子表");
                    mTvTime.setText("00:00:00");
                }
            }
        });


        mBtLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!state) {
                    mBtLight.setText("关闭手电");
                    state = true;
                    new Thread(new LightThread()).start();

                } else {
                    state = false;
                    mBtLight.setText("打开手电");
                    parameters.setFlashMode("off");
                    mCamera.setParameters(parameters);
                }
            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    public void onReceiveLocation(BDLocation location) {

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);

        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }

//         设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）

//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.mipmap.ic_launcher);
//        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
//        mBaiduMap.setMyLocationConfigeration(config);
        // 当不需要定位图层时关闭定位图层
        //        mBaiduMap.setMyLocationEnabled(false);
    }


    class Common {

        public static final String LOCATION = "location";
        public static final String LOCATION_ACTION = "locationAction";
    }

    private class LightThread implements Runnable {
        @Override
        public void run() {
            Message mMessage = mHandler.obtainMessage(msgToast);
            mHandler.sendMessage(mMessage);
            parameters = mCamera.getParameters();
            parameters.setFlashMode("torch");
            mCamera.setParameters(parameters);
        }
    }

    public class mWatchThread implements Runnable {
        @Override
        public void run() {
            System.out.print("线程还在运行^^^^^^^^^^^^^^^^^^");
            while (!mmmWatchThreads.isInterrupted()) {
                try {
                    Thread.sleep(1000);
//                    Message msg = new Message();
//                    msg.what = msgkey;
                    Message msg = mHandler.obtainMessage(msgkey);
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(String mLocation) {
//        mTextView.setText(message.getMenuDataVo().getTitle());
    }
}
