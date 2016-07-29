package cn.itcast.huayu.hellowworld.model.menu;

import java.io.Serializable;

/**
 * @author ln：zpf on 2016/7/29
 */
public class StepsBean implements Serializable{


    /**
     * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_f3731d35d1c1f116.jpg
     * step : 1.黄芽白菜心1棵
     */

    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
