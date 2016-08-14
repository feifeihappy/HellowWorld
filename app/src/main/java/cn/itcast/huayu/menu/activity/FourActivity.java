package cn.itcast.huayu.menu.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itcast.huayu.menu.R;

/**
 * Created by hao on 2016/8/14.
 */
public class FourActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        ButterKnife.bind(this);
        String mValue = getIntent().getStringExtra("key");
        textView.setText(mValue);
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
    protected void onStart() {
        super.onStart();
    }


}
