package cn.itcast.huayu.menu.cache;

import com.baidu.location.BDLocation;

import java.io.Serializable;

/**
 * Created by hao on 2016/8/7.
 */
public class GlobalCache implements Serializable {

    private static GlobalCache mInstance = null;

    public static GlobalCache newInstance() {
        if (mInstance == null) {
            mInstance = new GlobalCache();
        }
        return mInstance;
    }

    private StringBuffer mLocation;

    private BDLocation bdLocation;

    public StringBuffer getmLocation() {
        return mLocation;
    }

    public void setmLocation(StringBuffer mLocation) {
        this.mLocation = mLocation;
    }

    public BDLocation getisBdLocation() {
        return bdLocation;
    }

    public void setisBdLocation(BDLocation bdLocation) {
        this.bdLocation = bdLocation;
    }
}
