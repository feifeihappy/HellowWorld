package cn.itcast.huayu.menu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.adapter.FragmentTwoListAdapter;
import cn.itcast.huayu.menu.common.EventMessageCode;
import cn.itcast.huayu.menu.greendao.User;
import cn.itcast.huayu.menu.greendao.UserDao;
import cn.itcast.huayu.menu.model.menu.MenuListData;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @author ln：zpf on 2016/7/29
 *
 * http://blog.csdn.net/lmj623565791/article/details/46695347
 * PercentRelativeLayout,百分比布局
 */
@EFragment(R.layout.fragment_two)
public class FragmentTwo extends BaseFragment {
    public static FragmentTwo_ instance = null;
    @ViewById(R.id.tv_two)
    TextView mTextView;

    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.bt_data)
    Button btData;
    @BindView(R.id.listv)
    ListView listv;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.bt_database)
    Button btDatabase;
    @BindView(R.id.et_two)
    EditText etTwo;
    private Unbinder unbinder;
    private FragmentTwoListAdapter mFragmentTwoListAdapter;

    public FragmentTwo() {
    }

    public static FragmentTwo_ newInstance() {
        if (instance == null) {
            instance = new FragmentTwo_();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @AfterViews
    void initView() {

    }


    @Override
    public void onStart() {
        super.onStart();
        unbinder = ButterKnife.bind(this, getView());
        //查询数据库,用户列表
        List<User> mUser = mApp.getmAppCache().getDaoSession().getUserDao().loadAll();
        mFragmentTwoListAdapter = new FragmentTwoListAdapter(mUser, getActivity());
        listv.setAdapter(mFragmentTwoListAdapter);
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
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(MenuListData message) {
        if (message.tagFragmentone == EventMessageCode.TAG_FRAGMENTONE) {
            mTextView.setText(message.menuDataVo.getTitle());
        }
        Log.e("TAG", "helloEventBus: FragmentTwo");
    }


    @OnClick(R.id.bt_data)
    public void onClickButton() { 
        String mdata = et.getText().toString();
        et.setText("");
        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = df.format(new Date());
//        User mUser = new User(null,mdata,comment,100000L);
        User mUser = new User();

        mUser.setName(mdata);
        mUser.setTime(comment);
        mUser.setAge(11L);
        mApp.getmAppCache().getDaoSession().getUserDao().insert(mUser);
    }

    @OnClick(R.id.bt_database)
    public void onClickData() {
        String mdata = etTwo.getText().toString();
        etTwo.setText("");
        List<User> mUserData = mApp.getmAppCache().getDaoSession().getUserDao().queryBuilder()
                .where(UserDao.Properties.Name.eq(mdata))
                .list();
        mFragmentTwoListAdapter.setData(mUserData);
        mFragmentTwoListAdapter.notifyDataSetChanged();

    }


}
