package cn.itcast.huayu.menu.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * greendao.3.0
 * 新建entity编译一下自动生成DaoMaster 、DaoSession、Dao
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
 * 数据库插入查询
 * http://blog.csdn.net/xushuaic/article/details/24496191
 *
 */
@Entity
public class User {

    @Id
    private Long id;
    private String name;
    private String time;
    private Long age;
    public Long getAge() {
        return this.age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 802518300)
    public User(Long id, String name, String time, Long age) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }


    //下面省去了 setter/getter
}