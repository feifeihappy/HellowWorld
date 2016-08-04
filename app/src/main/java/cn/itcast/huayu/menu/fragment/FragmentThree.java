package cn.itcast.huayu.menu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.itcast.huayu.menu.R;

/**
 * @author ln：zpf on 2016/7/29
 */
public class FragmentThree extends BaseFragment {
    public static FragmentThree instance = null;
    final int msgkey = 1;
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
            }

        }
    };
    private Button mButton;
    private Thread mThread;

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
        mButton = (Button) view.findViewById(R.id.bt_button);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThread.interrupt();
                mTvTime.setText("");
            }
        });
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
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
        });
        mThread.start();
    }
}
