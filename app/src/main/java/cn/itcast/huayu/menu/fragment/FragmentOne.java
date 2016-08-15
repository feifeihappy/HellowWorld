package cn.itcast.huayu.menu.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.activity.MenuActivity;
import cn.itcast.huayu.menu.adapter.RecyclerViewAdapter;
import cn.itcast.huayu.menu.common.DividerItemDecoration;
import cn.itcast.huayu.menu.model.ResponseBaseEntity;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;
import cn.itcast.huayu.menu.model.menu.MenuListData;
import cn.itcast.huayu.menu.model.menu.MenuResult;
import cn.itcast.huayu.menu.util.LogUtil;
import cn.itcast.huayu.menu.util.ToastUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.greenrobot.event.EventBus;

/**
 * @author ln：zpf on 2016/7/29
 */
@EFragment(R.layout.fragment_one)
public class FragmentOne extends BaseFragment implements
        RecyclerViewAdapter.Callback, SwipeRefreshLayout.OnRefreshListener {
    public static final int FISTERACTIVITY = 1;
    private static final int DATA_COUNT = 60;


    private static final int SET_ENABLED = 2;
    public static FragmentOne_ instance = null;
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    List<MenuDataVo> mData = null;
    @BindView(R.id.bt_evetntbus)
    Button btEvetntbus;
    @BindView(R.id.bt_evetntbustow)
    Button btEvetntbustow;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private EditText mEditContent;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mLIst;
    private int mNum;
    private int i = 1;
    private Button mBtCommit;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_ENABLED:
                    setEnabled(mBtCommit, true);
                    break;
                case 3:

                    break;
                default:
                    break;

            }
        }

    };
    private Integer mDataPosition;

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
        ButterKnife.bind(this, getView());

        //集合
        mLIst = new ArrayList<>();
        mLIst.add("酸");
        mLIst.add("甜");
        mLIst.add("苦");
        mLIst.add("辣");
        mLIst.add("鲁菜");
        mLIst.add("川菜");
        mLIst.add("粤菜");
        mLIst.add("面");
        mLIst.add("包子");
        mLIst.add("饺子");
        mLIst.add("上海");
    }


    @Override
    public void onResume() {
        super.onResume();
        initView();

    }

    protected void initView() {
        //EditText不弹出软键盘

        hideKeyboard();

        //        DataRequest();
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(FragmentOne.this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //第一次进入界面立即刷新
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);
        SwipeRefreshLayout.OnRefreshListener mListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        DataRequest("酸");
                    }
                }, 2000);
            }
        };
        mListener.onRefresh();
        ToastUtil.showToast(getActivity(), "加载:FragmentOne_onResume调用'酸这个接口'");
        mEditContent = (EditText) getView().findViewById(R.id.edit_content);
        mEditContent.clearFocus();
        mBtCommit = (Button) getView().findViewById(R.id.bt_commit);


        mBtCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                setEnabled(mBtCommit, false);

                final String mContent = mEditContent.getText().toString();
                if (!mContent.equals("")) {
                    showloadingDialog();
                    DataRequest(mContent);
                } else {
                    ToastUtil.showToast(getActivity(), "请输入内容");
                }
                long j = i++;
                LogUtil.getInstance().error(String.valueOf(j));
            }
        });
    }

    private void setEnabled(View view, boolean enabled) {
        if (enabled) {
            view.setEnabled(true);
        } else {
            view.setEnabled(false);
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getView().getApplicationWindowToken(), 0);

        }
    }

    @Background
    void DataRequest(String mContent) {
        try {
            ResponseBaseEntity<MenuResult> result = mMenuService.getMenu(mContent, "3d7de91fec4a37c9b9481ea036f59846");
            mData = result.getResult().getData();
            mDataPosition = null;
            setAdapter();
        } catch (Exception e) {
            hideLoadingDialog();
            showToas(e.toString());
            mHandler.obtainMessage(SET_ENABLED).sendToTarget();
        }
    }

    @UiThread
    void setAdapter() {
        if (mRecyclerViewAdapter != null) {
            hideLoadingDialog();
            mRecyclerViewAdapter.setData(mData);
            mRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            hideLoadingDialog();
            mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mData, FragmentOne.this);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                    DividerItemDecoration.VERTICAL_LIST));
        }

        setEnabled(mBtCommit, true);
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
     * @param itemId
     * @param layoutPosition
     */
    @Override
    public void viewClick(View v, int AdapterPosition, long itemId, int layoutPosition) {
        final Integer mItemViewPosition = (Integer) v.getTag();
        LogUtil.getInstance().error("mItemViewPositionv_________" + String.valueOf(AdapterPosition)
                + "_____AdapterPosition______" + String.valueOf(mItemViewPosition) +
                "___itemId____" + String.valueOf(itemId) + "__layoutPosition___" + String.valueOf(layoutPosition));

        mDataPosition = layoutPosition - 1;//数据的位置
        ToastUtil.showToast(getActivity(), "第" + layoutPosition + "item");
        //简单的dialog
                  /*  new SweetAlertDialog(mcontext)
                            .setTitleText("Here's a message!")
                            .show();*/

        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("你确定?")
                .setContentText("MenuActivity".substring(4, 12).replace("A", "a"))
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
                        Intent intent = new Intent(getActivity(), MenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mData", mData.get(mDataPosition));
                        intent.putExtras(bundle);
                        startActivityForResult(intent, FISTERACTIVITY);
                    }
                })
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FISTERACTIVITY:
                if (resultCode == Activity.RESULT_OK) {

                    Bundle b = data.getExtras();
                    String m = b.getString("MenuActivity");
                    LogUtil.getInstance().debug(m);
                    ToastUtil.showToast(getActivity(), m);
                } else {
                    return;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //生成随机菜单
                String mMenu = getRandomMenu();
                showLongToast("刷新后调用随机接口" + String.valueOf(mNum) + mMenu);
                mSwipeRefreshLayout.setRefreshing(false);
                DataRequest(mMenu);
            }
        }, 2000);
    }

    private String getRandomMenu() {
        Random mRandom = new Random();
        mNum = mRandom.nextInt(11);
        return mLIst.get(mNum);
    }

    @OnClick(R.id.bt_evetntbus)
    public void onClick() {
        if (mData != null && mDataPosition != null) {

            EventBus.getDefault().post(new MenuListData(mData.get(mDataPosition)));
        }
    }

    @OnClick(R.id.bt_evetntbustow)
    public void onClickTwo() {
        EventBus.getDefault().post(new MenuListData(mData.get(mDataPosition)));

    }

}
