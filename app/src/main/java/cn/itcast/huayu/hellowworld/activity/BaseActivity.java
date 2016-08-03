package cn.itcast.huayu.hellowworld.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.rest.RestService;

import cn.itcast.huayu.hellowworld.MyApplication;
import cn.itcast.huayu.hellowworld.network.WeatherService;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 处理Activity公共特性
 * Created by xu on 2015-06-09.
 */
@EActivity
@WindowFeature(Window.FEATURE_NO_TITLE)
public abstract class BaseActivity
        extends AppCompatActivity {

    @App
    public MyApplication mApp;
    @RestService
    protected WeatherService Service;
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (pDialog == null) {
            pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper()
                    .setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
        }
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

    @UiThread
    public void showloadingDialog() {
        if (this != null && pDialog != null) {
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    @UiThread
    public void showloadingDialog(boolean cancel) {
        if (this != null && pDialog != null) {
            pDialog.setCancelable(cancel);
            pDialog.show();
        }
    }


    /**
     * 显示异常
     *
     * @param exception
     */
    @UiThread
    public void showToast(Exception exception) {
        Toast.makeText(this, exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * 显示短时间消息
     *
     * @param msg
     */
    @UiThread
    public void showToast(String msg) {
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 显示长时间消息
     *
     * @param msg
     */
    @UiThread
    public void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
