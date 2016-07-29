package cn.itcast.huayu.hellowworld.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

import cn.itcast.huayu.hellowworld.R;
import cn.itcast.huayu.hellowworld.model.ResponseBaseEntity;
import cn.itcast.huayu.hellowworld.model.WeatherResult;
import cn.itcast.huayu.hellowworld.model.menu.MenuDataVo;
import cn.itcast.huayu.hellowworld.model.menu.MenuResult;
import cn.itcast.huayu.hellowworld.network.Menu;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @RestService
    Menu mMenuService;



    @AfterViews
    void initView() {
        System.out.println("视图初始化");

    }

    @Click(R.id.bt_button)
    void clickButton() {
//        Intent intent  = new Intent(this ,fister_.class);
//        startActivity(intent);
        weatherData();

    }

    @Click(R.id.bt_button_menu)
    void clickButtonMenu() {

        menuData();


    }

    @Background
    void menuData() {

        ResponseBaseEntity<MenuResult> result = mMenuService.getMenu("川菜", "3d7de91fec4a37c9b9481ea036f59846");
        List<MenuDataVo> mData = result.getResult().getData();



    }


    @Background
    void weatherData() {

        ResponseBaseEntity<WeatherResult> result = Service.getScantPlan("上海", "bb5336b483148c60699b59df9b926e2f");
        String temp = result.getResult().getSk().getTemp();

    }
}
