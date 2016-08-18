package cn.itcast.huayu.menu.cache;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cn.itcast.huayu.menu.MyApplication;
import cn.itcast.huayu.menu.greendao.DaoMaster;
import cn.itcast.huayu.menu.greendao.DaoMaster.DevOpenHelper;
import cn.itcast.huayu.menu.greendao.DaoSession;
import cn.itcast.huayu.menu.greendao.User;
import cn.itcast.huayu.menu.greendao.UserDao;


/**
 * @author ln：zpf on 2016/8/18
 */
public class AppCache {
    private final SQLiteDatabase db;
    private final DaoMaster daoMaster;
    private final DaoSession daoSession;
    private final UserDao userDao;

    public AppCache(MyApplication myApplication) {
        DevOpenHelper helper = new DaoMaster.DevOpenHelper(myApplication, "MyTest-db");
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
