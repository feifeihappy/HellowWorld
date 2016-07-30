package cn.itcast.huayu.hellowworld.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import cn.itcast.huayu.hellowworld.R;
import cn.itcast.huayu.hellowworld.adapter.RecyclerViewAdapter;
import de.greenrobot.event.EventBus;

/**
 * @author ln：zpf on 2016/7/29
 */
@EFragment(R.layout.fragment_one)
public class FragmentOne extends BaseFragment {
    private static final int DATA_COUNT = 60;
    public static FragmentOne_ instance = null;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String[] mData;

    public FragmentOne() {
    }

    public static FragmentOne_ newInstance() {
        if (instance == null) {
            instance = new FragmentOne_();
        }
        return instance;
    }

    @Override
    public void onAttach(Context context) {//当activity与fragment发生关联时调用
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inData();
    }

    private void inData() {
        mData = new String[DATA_COUNT];
        for (int i = 0; i < DATA_COUNT; i++) {
            mData[i] = "第" + i + "元素fefefef";
        }
    }

    /**
     * 创建fragment视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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


        RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
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

//    @Click(R.id.bt_button)
//    void clickButton() {
//        weatherData();
//    }
//
//    @Click(R.id.bt_button_menu)
//    void clickButtonMenu() {
//        menuData();
//    }
//
//    @Background
//    void menuData() {
//        ResponseBaseEntity<MenuResult> result = mMenuService.getMenu("川菜", "3d7de91fec4a37c9b9481ea036f59846");
//        List<MenuDataVo> mData = result.getResult().getData();
//    }
//
//    @Background
//    void weatherData() {
//        ResponseBaseEntity<WeatherResult> result = mWeatherService.getScantPlan("上海", "bb5336b483148c60699b59df9b926e2f");
//        String temp = result.getResult().getSk().getTemp();
//    }

}
