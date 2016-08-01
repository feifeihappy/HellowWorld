package cn.itcast.huayu.hellowworld.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import cn.itcast.huayu.hellowworld.R;
import cn.itcast.huayu.hellowworld.adapter.MyViewPagerAdapter;
import cn.itcast.huayu.hellowworld.fragment.FragmentOne;
import cn.itcast.huayu.hellowworld.fragment.FragmentThree;
import cn.itcast.huayu.hellowworld.fragment.FragmentTwo;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;
    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @AfterViews
    void initView() {
        System.out.println("视图初始化");
        MyViewPagerAdapter mFragmentAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mFragmentAdapter.addfragment(FragmentOne.newInstance(), "菜谱");
        mFragmentAdapter.addfragment(FragmentTwo.newInstance(), "天气");
        mFragmentAdapter.addfragment(FragmentThree.newInstance(), "手机号");
        mViewPager.setAdapter(mFragmentAdapter);
//        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.addTab(mTabLayout.newTab().setText("第一个fragment"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("第二个fragment"));
        mTabLayout.addTab(mTabLayout.newTab().setText("第三个fragment"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联Vi
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
