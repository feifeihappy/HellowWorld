package cn.itcast.huayu.menu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;

/**
 * @author ln：zpf on 2016/7/26
 */
public class MenuActivity extends BaseActivity {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private MenuDataVo mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
         mData = (MenuDataVo) getIntent().getSerializableExtra("mData");

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {//fef
        super.onBackPressed();

    }
}
