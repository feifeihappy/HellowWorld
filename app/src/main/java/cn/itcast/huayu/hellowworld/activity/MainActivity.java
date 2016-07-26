package cn.itcast.huayu.hellowworld.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import cn.itcast.huayu.hellowworld.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    @AfterViews
    void initView() {
        System.out.println("视图初始化");

    }

    @Click(R.id.bt_button)
    void clickButton() {
//        Intent intent  = new Intent(this ,fister_.class);
//        startActivity(intent);
        pullData();

    }

    @Background
    void pullData() {

        Service.getScantPlan("上海","bb5336b483148c60699b59df9b926e2f");
    }
}
