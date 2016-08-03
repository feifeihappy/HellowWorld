package cn.itcast.huayu.hellowworld.activity;

import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;

import java.util.List;

import cn.itcast.huayu.hellowworld.R;
import cn.itcast.huayu.hellowworld.model.menu.MenuDataVo;
import cn.itcast.huayu.hellowworld.model.menu.MenuListData;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @author ln：zpf on 2016/7/26
 */
@EActivity(R.layout.activity_first)
public class FisterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MenuDataVo mData = (MenuDataVo) getIntent().getSerializableExtra("mData");
        TextView mTextView = (TextView) findViewById(R.id.tv_first);
        mTextView.setText(mData.getTitle());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
