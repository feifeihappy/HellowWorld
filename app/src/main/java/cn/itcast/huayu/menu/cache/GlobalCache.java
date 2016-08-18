package cn.itcast.huayu.menu.cache;

/**
 * Created by hao on 2016/8/7.
 */
public class GlobalCache {

    private static GlobalCache mInstance = null;
    private static StringBuffer mLocation;

    public static GlobalCache newInstance() {
        if (mInstance == null) {
            mInstance = new GlobalCache();
        }
        return mInstance;
    }

    public static StringBuffer getmLocation() {
        return mLocation;
    }

    public static void setmLocation(StringBuffer mLocation) {
        GlobalCache.mLocation = mLocation;
    }
}
