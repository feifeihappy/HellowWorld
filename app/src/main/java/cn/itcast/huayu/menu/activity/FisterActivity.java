package cn.itcast.huayu.menu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;

import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;

/**
 * @author ln：zpf on 2016/7/26
 */
public class FisterActivity extends BaseActivity {


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MenuDataVo mData = (MenuDataVo) getIntent().getSerializableExtra("mData");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
