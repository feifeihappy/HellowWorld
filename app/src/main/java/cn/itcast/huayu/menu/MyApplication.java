package cn.itcast.huayu.menu;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import org.androidannotations.annotations.EApplication;

import cn.itcast.huayu.menu.cache.GlobalCache;

/**
 * @author ln：zpf on 2016/7/26
 */
@EApplication
public class MyApplication extends Application {


    private static GlobalCache mGlobalCache;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "7e8f387dbf", true);
        initCache();

    }

    public static void initCache() {
        if (mGlobalCache == null){
            mGlobalCache = new GlobalCache();
        }
    }

     public GlobalCache getmGlobalCache() {
        return mGlobalCache;
    }
}
