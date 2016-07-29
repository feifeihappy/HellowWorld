package cn.itcast.huayu.hellowworld.network;


import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

import cn.itcast.huayu.hellowworld.model.ResponseBaseEntity;
import cn.itcast.huayu.hellowworld.model.WeatherResult;

/**
 * 天气预报
 * Created by xu on 2015-05-11.
 */

@Rest(rootUrl = IService.Url, converters = {JacksonIgnorePropertie2HttpMessageConverter.class}, interceptors = {HttpBasicInterceptor.class}, requestFactory = MyHttpComponentsClientHttpRequestFactory.class)
public interface WeatherService {

    @Get("?cityname={cityname}&key={key}")
    ResponseBaseEntity<WeatherResult> getScantPlan(String cityname, String key);


}
