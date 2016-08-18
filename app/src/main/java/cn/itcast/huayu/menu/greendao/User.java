package cn.itcast.huayu.menu.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * greendao.3.0
 * 编译一下自动生成DaoMaster 、DaoSession、Dao
 *
 * @author zpf
 * @mail zpfworking@sina.com
 * @createtime 2016/8/18 16:08
 * GreenDao 介绍
 * http://www.cnblogs.com/whoislcj/p/5651396.html
 *
 *  数据库的几个概念：主键，外键，索引，唯一索引
 * http://blog.csdn.net/xrt95050/article/details/5556411
 *
 */
@Entity
public class User {

    @Id(autoincrement = true)
    private long id;
    private String name;
    private String time;
    private int age;

    @Generated(hash = 785255723)
    public User(long id, String name, String time, int age) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    //下面省去了 setter/getter
}