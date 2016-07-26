package cn.itcast.huayu.hellowworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {


    @AfterViews
    void initView() {
        System.out.println("视图初始化");

    }

    @Click(R.id.bt_button)
    void clickButton() {
        Intent intent  = new Intent(this ,fister_.class);
        startActivity(intent);

    }
}
