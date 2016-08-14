package cn.itcast.huayu.menu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.bugly.crashreport.CrashReport;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.ViewById;

import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.model.menu.MenuListData;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @author ln：zpf on 2016/7/29
 */
@EFragment(R.layout.fragment_two)
public class FragmentTwo extends BaseFragment {
    public static FragmentTwo_ instance = null;
    @ViewById(R.id.tv_two)
    TextView mTextView;
    @ViewById(R.id.iv_two)
    ImageView mImage;
    @ViewById(R.id.bt)
    Button mButton;

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
    @Click(R.id.bt)
    void bt(){

        String url = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/52/5198_4cde66e2c75c9abe.jpg";
        Glide
                .with(this)
                .load(url)
                .override(500,500)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(mImage);

    }
    @LongClick(R.id.bt)
    void longBt(){
        CrashReport.testJavaCrash();

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
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(MenuListData message) {
        mTextView.setText(message.menuDataVo.getTitle());
    }
}
