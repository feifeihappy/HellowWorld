package cn.itcast.huayu.hellowworld.network;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

import cn.itcast.huayu.hellowworld.model.ResponseBaseEntity;
import cn.itcast.huayu.hellowworld.model.menu.MenuResult;

/**
 * @author ln：zpf on 2016/7/29
 */
@Rest(rootUrl = IService.MenuUrl, converters = JacksonIgnorePropertie2HttpMessageConverter.class, interceptors = HttpBasicInterceptor.class, requestFactory = MyHttpComponentsClientHttpRequestFactory.class)
public interface Menu {
    @Get("?menu={menu}&key={key}")
    ResponseBaseEntity<MenuResult> getMenu(String menu, String key);

}
