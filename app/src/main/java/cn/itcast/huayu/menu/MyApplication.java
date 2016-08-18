package cn.itcast.huayu.menu;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.bugly.crashreport.CrashReport;

import org.androidannotations.annotations.EApplication;

import cn.itcast.huayu.menu.cache.AppCache;

/**
 * @author ln：zpf on 2016/7/26
 */
@EApplication
public class MyApplication extends Application {


    private AppCache mAppCache;

    public AppCache getmAppCache() {
        return mAppCache;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        CrashReport.initCrashReport(getApplicationContext(), "7e8f387dbf", true);
        initCache();
    }

    private void initCache() {
        mAppCache = new AppCache(this);
    }


}
