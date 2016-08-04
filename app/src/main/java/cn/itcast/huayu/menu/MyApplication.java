package cn.itcast.huayu.menu;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import org.androidannotations.annotations.EApplication;

/**
 * @author ln：zpf on 2016/7/26
 */
@EApplication
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "7e8f387dbf", true);


    }
}
