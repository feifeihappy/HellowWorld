package cn.itcast.huayu.hellowworld.model.menu;

import java.io.Serializable;
import java.util.List;

/**
 * @author ln：zpf on 2016/7/29
 */
public class MenuDataVo implements Serializable{


    /**
     * id : 6511
     * title : 不辣的开水白菜—学做川菜
     * tags : 汤;辣;10分钟内;汤锅;1-2人
     * imtro : 春 川菜被大多数人认为是麻辣的，不麻不辣那不是川菜。而这道开水白菜原系川菜名厨黄敬临在清宫御膳房时创制，后黄敬临将此菜制法带回四川，广为流传。成为饭店高档筵席上的一味佳肴，其关键在于吊汤，成菜。 这开水白菜看似简单，其实吊汤的品质很重要，决定着菜的口味。我这是山寨版的，用的自家老鸡汤，没有那么讲究地吊汤，就是用一只老母鸡、一些鲜笋、金华火腿片熬制而成。 鸡汤的营养自不必多说了。
     * ingredients : 白菜心,200g
     * burden : 白胡椒粉,4g;盐,3g;清鸡汤,200ml
     * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/7/6511_355812.jpg"]
     * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_f3731d35d1c1f116.jpg","step":"1.黄芽白菜心1棵"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_044ec4fca1f1ca6c.jpg","step":"2.用手掰开白菜心，洗净放入锅中。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_98468e135e8d20a0.jpg","step":"3.吊制老鸡汤"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_badfb6e16bd43cee.jpg","step":"4.白菜中放入白胡椒粉，加入鸡汤，煮开，至白菜煮塌了，加盐调味关火。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_27e12d1399bf80ef.jpg","step":"5.装盘。"}]
     */

    private String id;
    private String title;
    private String tags;
    private String imtro;
    private String ingredients;
    private String burden;
    private List<String> albums;
    /**
     * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/66/6511_f3731d35d1c1f116.jpg
     * step : 1.黄芽白菜心1棵
     */

    private List<StepsBean> steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<StepsBean> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsBean> steps) {
        this.steps = steps;
    }


}
