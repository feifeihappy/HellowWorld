package cn.itcast.huayu.menu.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
public class FragmentThree extends BaseFragment {
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

    public FragmentThree() {
    }

    public static FragmentThree newInstance() {
        if (instance == null) {
            instance = new FragmentThree();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        mButtonWatch = (Button) view.findViewById(R.id.bt_button);
        mBtLight = (Button) view.findViewById(R.id.bt_light);
        mTvLocation = (TextView) view.findViewById(R.id.tv_location);
        mCamera = Camera.open();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
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


        // 注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Common.LOCATION_ACTION);
        getActivity().registerReceiver(new LocationBroadcastReceiver(), filter);
        StringBuffer mLocation = GlobalCache.newInstance().getmLocation();
        //获取定位位置
        mTvLocation.setText(mLocation);



    }

    class LocationBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(Common.LOCATION_ACTION))
                return;
            String locationInfo = intent.getStringExtra(Common.LOCATION);
//            getActivity().unregisterReceiver(this);// 不需要时注销
        }
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
