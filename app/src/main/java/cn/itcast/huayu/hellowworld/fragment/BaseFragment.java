package cn.itcast.huayu.hellowworld.fragment;

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

import cn.itcast.huayu.hellowworld.MyApplication;
import cn.itcast.huayu.hellowworld.network.Menu;
import cn.itcast.huayu.hellowworld.network.WeatherService;
import cn.itcast.huayu.hellowworld.util.ToastUtil;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Toast.makeText(getActivity(),mes,Toast.LENGTH_SHORT).show();
    }

}
