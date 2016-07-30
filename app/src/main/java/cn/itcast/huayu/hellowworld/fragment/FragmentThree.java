package cn.itcast.huayu.hellowworld.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.itcast.huayu.hellowworld.R;

/**
 * @author ln：zpf on 2016/7/29
 */
public class FragmentThree extends BaseFragment{
    public FragmentThree(){}
    public static FragmentThree instance = null;

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

        return view;
    }
}
