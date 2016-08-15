package cn.itcast.huayu.menu.model.menu;

/**
 * @author ln：zpf on 2016/8/3
 */
public class MenuListData {
    /**
     * 关于final的重要知识点
     * <p/>
     * 1.不能够对final变量再次赋值
     * 2.在匿名类中所有变量都必须是final变量
     * 3.final方法不能被重写
     * 4.将类、方法、变量声明为final能够提高性能，这样JVM就有机会进行估计，然后优化
     * 5.按照Java代码惯例，final变量就是常量，而且通常常量名要大写
     */
    public MenuDataVo menuDataVo;

    public MenuListData(MenuDataVo menuDataVo) {
        this.menuDataVo = menuDataVo;
    }

    public static class FragmentOne{





    }


}
