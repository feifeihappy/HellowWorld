package cn.itcast.huayu.menu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.activity.FisterActivity_;
import cn.itcast.huayu.menu.adapter.RecyclerViewAdapter;
import cn.itcast.huayu.menu.common.DividerItemDecoration;
import cn.itcast.huayu.menu.model.ResponseBaseEntity;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;
import cn.itcast.huayu.menu.model.menu.MenuListData;
import cn.itcast.huayu.menu.model.menu.MenuResult;
import cn.itcast.huayu.menu.util.ToastUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.greenrobot.event.EventBus;

/**
 * @author ln：zpf on 2016/7/29
 */
@EFragment(R.layout.fragment_one)
public class FragmentOne extends BaseFragment implements RecyclerViewAdapter.Callback {
    private static final int DATA_COUNT = 60;
    public static FragmentOne_ instance = null;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    List<MenuDataVo> mData = null;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private EditText mEditContent;

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
//        DataRequest();
        mEditContent = (EditText) getView().findViewById(R.id.edit_content);
        Button mBtCommit = (Button) getView().findViewById(R.id.bt_commit);
        //EditText不弹出软键盘
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditContent.getWindowToken(), 0);

        mBtCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mContent = mEditContent.getText().toString();
                if (!mContent.equals("")) {
                    showloadingDialog();
                    DataRequest(mContent);
                } else {
                    ToastUtil.showToast(getActivity(), "请输入内容");
                }
            }
        });
    }

    @Background
    void DataRequest(String mContent) {
        try {
            ResponseBaseEntity<MenuResult> result = mMenuService.getMenu(mContent, "3d7de91fec4a37c9b9481ea036f59846");
            mData = result.getResult().getData();
            setAdapter();
        } catch (Exception e) {
            hideLoadingDialog();
            showToas(e.toString());
        }
    }

    @UiThread
    void setAdapter() {
        hideLoadingDialog();
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mData, FragmentOne.this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
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

    /**
     * RecyclerViewAdapter的回调函数
     *
     * @param v
     */
    @Override
    public void viewClick(View v) {
        final Integer mItemViewPosition = (Integer) v.getTag();
        final int mDataPosition = mItemViewPosition - 1;//数据的位置
        EventBus.getDefault().post(new MenuListData(mData.get(mDataPosition)));
        ToastUtil.showToast(getActivity(), "第" + mItemViewPosition + "item");
        //简单的dialog
                  /*  new SweetAlertDialog(mcontext)
                            .setTitleText("Here's a message!")
                            .show();*/

        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("你确定?")
                .setContentText("FisterActivity".substring(6, 14).replace("A", "a"))
                .setCancelText("取消")
                .setConfirmText("是的")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        Intent intent = new Intent(getActivity(), FisterActivity_.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mData", mData.get(mDataPosition));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
                .show();
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
