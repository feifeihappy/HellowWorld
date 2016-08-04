package cn.itcast.huayu.menu.model.menu;

import java.io.Serializable;
import java.util.List;

/**
 * @author ln：zpf on 2016/7/29
 */
public class MenuResult implements Serializable{

public MenuResult(){

}
    /**
     * data : []
     * totalNum : 4
     * pn : 0
     * rn : 30
     */

    private String totalNum;
    private int pn;
    private int rn;
    private List<MenuDataVo> data;

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public List<MenuDataVo> getData() {
        return data;
    }

    public void setData(List<MenuDataVo> data) {
        this.data = data;
    }
}
