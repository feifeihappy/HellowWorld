package cn.itcast.huayu.menu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.itcast.huayu.menu.R;
import cn.itcast.huayu.menu.adapter.MenuAdapter;
import cn.itcast.huayu.menu.model.menu.MenuDataVo;
import cn.itcast.huayu.menu.model.menu.StepsBean;

/**
 * @author ln：zpf on 2016/7/26
 */
public class MenuActivity extends BaseActivity {


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @BindView(R.id.iv_albums)
    ImageView ivAlbums;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_burden)
    TextView tvBurden;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.rt)
    LinearLayout rt;
    private MenuDataVo mData;
    private MenuAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);


        mData = (MenuDataVo) getIntent().getSerializableExtra("mData");
        List<StepsBean> stepsData = mData.getSteps();
                mListAdapter = new MenuAdapter(this,stepsData);
        lv.setAdapter(mListAdapter);
        //图片
        List<String> mUrlList = mData.getAlbums();
        String mUrl = mUrlList.get(0);
        Glide
                .with(this)
                .load(mUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(ivAlbums);
        tvTitle.setText(mData.getTitle());
        tvIntro.setText(mData.getImtro());

        String mString = mData.getBurden().replace(",", "                   ");
        String mBurden = mString.replace(";", "\n");
        tvBurden.setText(mBurden);





    }


    @Override
    protected void onStart() {
        super.onStart();
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
    public void onBackPressed() {//fef
        super.onBackPressed();

    }
}
