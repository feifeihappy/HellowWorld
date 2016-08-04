package cn.itcast.huayu.menu.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import cn.itcast.huayu.menu.MyApplication;
import cn.itcast.huayu.menu.network.Menu;
import cn.itcast.huayu.menu.network.WeatherService;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author ln：zpf on 2016/7/29
 */
@EFragment
public class BaseFragment extends Fragment {
    @App
    public MyApplication mApp;
    @RestService
    protected WeatherService mWeatherService;

    @RestService
    Menu mMenuService;
    private SweetAlertDialog pDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (pDialog == null) {
            pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper()
                    .setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @UiThread
    void showToas(String mes) {
        Toast.makeText(getActivity(), mes, Toast.LENGTH_SHORT).show();
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

    @UiThread
    public void hideLoadingDialog() {
        if (this != null && pDialog != null) {
            pDialog.hide();
        }
    }

}
